package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * @author huzihao
 * @since 2020/9/24 22:11
 */
public class JDBCUtil {
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
     * @param values 占位符对应的值
     */
    public static void execute(String sql, List<Object> values) {
        var size = (int) sql.chars().filter(ch -> '?' == ch).count();
        if (size != values.size()) throw new IllegalArgumentException("占位符和值的数量不同");

        try (var cxn = getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            for (var i = 0; i < size; i++) {
                stmt.setObject(i + 1, values.get(i));
            }
            stmt.execute();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
