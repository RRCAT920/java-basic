package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * 在配置文件（默认database.properties）中
 * <ol>
 *     <li>
 *         必须提供：
 *         <ul>
 *             <li>url</li>
 *             <li>user</li>
 *             <li>password</li>
 *             <li>driverClass</li>
 *         </ul>
 *     </li>
 *     <li>
 *         可选提供:
 *         <ul>
 *             <li>sql</li>
 *             <li>holderValues（值用,分隔且不要在最后加,）</li>
 *         </ul>
 *     </li>
 * </ol>
 * @author huzihao
 * @since 2020/9/24 22:11
 * @see JDBCUtils#databaseFile
 * @see JDBCUtils#url
 * @see JDBCUtils#user
 * @see JDBCUtils#password
 * @see JDBCUtils#sql
 * @see JDBCUtils#holderValues
 */
public final class JDBCUtils {
    private static File databaseFile = new File("database.properties");
    private static String url;
    private static String user;
    private static String password;
    private static String sql;
    private static List<Object> holderValues;

    private static void load() {
        try (final var in = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(databaseFile.getName())) {
            final var prop = new Properties();
            prop.load(in);
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            password = prop.getProperty("password");
            sql = prop.getProperty("sql");
            holderValues = getHolderValueList(prop.getProperty("holderValues"));
            Class.forName(prop.getProperty("driverClass"));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置数据库配置路径
     * @param path 配置路径
     */
    public static void setPath(String path) {
        JDBCUtils.databaseFile = new File(path);
        load();
    }

    /**
     * 获得数据库连接
     * @return 数据库连接
     */
    // 为了代码兼容设置成public
    public static Connection getConnection() throws SQLException {
        load();
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * 通用的增删改（没有查）
     * @param sql 要执行的SQL语句
     * @param holderValues 占位符对应的值
     */
    public static void execute(String sql, List<Object> holderValues) {
//        Want to be more user-friendly add this
//        var size = (int) sql.chars().filter(ch -> '?' == ch).count();
//        if (size != holderValues.size()) throw new IllegalArgumentException("占位符和值的数量不同");

        try (var cxn = getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            for (var i = 0; null != holderValues && i < holderValues.size(); i++) {
                stmt.setObject(i + 1, holderValues.get(i));
            }
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void execute() {
        execute(sql, holderValues);
    }

    /**
     * 基本查询
     * @see JDBCUtils#query
     */
    public static <T> List<T> query(Class<T> aClass) {
        return query(aClass, sql, holderValues);
    }

    /**
     * 返回查询结果对应对象
     * @param aClass 表对应的Java类.class
     * @param sql SQL语句
     * @param holderValues 占位符的值
     * @param <T> 表对应的Java类
     * @return 表对应的Java类的一个对象
     */
    public static <T> List<T> query(Class<T> aClass, String sql, List<Object> holderValues) {
        var list = new ArrayList<T>();
        try (final var cxn = getConnection();
             final var stmt = cxn.prepareStatement(sql)) {
            for (int i = 0; null != holderValues && i < holderValues.size(); i++) {
                stmt.setObject(i + 1, holderValues.get(i));
            }
            try (final var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    final var metaData = resultSet.getMetaData();
                    var instance = aClass.getDeclaredConstructor().newInstance();
                    for (int i = 0; i < metaData.getColumnCount(); i++) {
                        final var field = aClass.getDeclaredField(
                                metaData.getColumnLabel(i + 1));
                        field.setAccessible(true);
                        field.set(instance, resultSet.getObject(i + 1));
//                    System.out.println(metaData.getColumnTypeName(i + 1));
                    }
                    list.add(instance);
                }
            }
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException |
                NoSuchFieldException | SQLException | InstantiationException e) {
            e.printStackTrace();
        }
        return 0 == list.size() ? null : list;
    }

    private static List<Object> getHolderValueList(String holderValues) {
        if (null == holderValues) return null;
        if (holderValues.contains(",")) return Arrays.asList(holderValues.split(","));
        return Collections.singletonList(holderValues);
    }

    public static void main(String[] args) {
        System.out.println(getHolderValueList("1"));
    }
}
