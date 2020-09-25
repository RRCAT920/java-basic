package util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author huzihao
 * @since 2020/9/24 22:11
 */
public class JDBCUtils {
    /**
     * 根据配置文件（jdbc.properties）获得数据库连接
     * @return 数据库连接
     */
    // 为了代码兼容设置成public
    public static Connection getConnection() throws IOException, ClassNotFoundException,
            SQLException {
        var in = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("jdbc.properties");
        var prop = new Properties();
        prop.load(in);
        var url = prop.getProperty("url");
        var user = prop.getProperty("user");
        var password = prop.getProperty("password");
        var driverClass = prop.getProperty("driverClass");

        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 通用的增删改（没有查）
     * @param sql 要执行的SQL语句
     * @param holderValues 占位符对应的值
     */
    public static void execute(String sql, List<Object> holderValues) {
        var size = (int) sql.chars().filter(ch -> '?' == ch).count();
        if (size != holderValues.size()) throw new IllegalArgumentException("占位符和值的数量不同");

        try (var cxn = getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            for (var i = 0; i < size; i++) {
                stmt.setObject(i + 1, holderValues.get(i));
            }
            stmt.execute();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回查询结果对应对象
     * @param aClass 表对应的Java类.class
     * @param sql SQL语句
     * @param holderValues 占位符的值
     * @param <T> 表对应的Java类
     * @return 表对应的Java类的一个对象
     */
    public static <T> T query(Class<T> aClass, String sql, List<Object> holderValues) {
        T instance = null;
        try (final var cxn = getConnection();
             final var stmt = cxn.prepareStatement(sql)) {
            for (int i = 0; i < holderValues.size(); i++) {
                stmt.setObject(i + 1, holderValues.get(i));
            }
            final var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                final var metaData = resultSet.getMetaData();
                instance = aClass.getDeclaredConstructor().newInstance();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    final var field = aClass.getDeclaredField(metaData.getColumnLabel(i + 1));
                    field.setAccessible(true);
                    field.set(instance, resultSet.getObject(i + 1));
//                    System.out.println(metaData.getColumnTypeName(i + 1));
                }
            }
        } catch (IOException | InvocationTargetException | NoSuchMethodException |
                IllegalAccessException | NoSuchFieldException | SQLException |
                ClassNotFoundException | InstantiationException e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 基本查询
     * @see JDBCUtils#query
     */
    public static <T> T query(Class<T> aClass, String sql) {
        return query(aClass, sql, List.of());
    }
}
