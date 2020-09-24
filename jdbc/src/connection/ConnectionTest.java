package connection;

import com.mysql.cj.jdbc.Driver;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author huzihao
 * @since 2020/9/24 16:38
 */
public class ConnectionTest {
    @Test
    public void testCxn1() throws SQLException {
        var driver = new Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        var info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "tkl19981125");
        var cxn = driver.connect(url, info);

        System.out.println(cxn);
    }

    /**
     * 提高可移植性
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws SQLException
     */
    @Test
    public void testCxn2() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException,
            SQLException {
        var aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        var driver = (Driver) aClass.getDeclaredConstructor().newInstance();
        var url = "jdbc:mysql://localhost:3306/test";
        var info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "tkl19981125");
        var cxn = driver.connect(url, info);

        System.out.println(cxn);
    }

    /**
     * 使用DriverManager
     */
    @Test
    public void testCxn3() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException,
            SQLException {
        var aClass = Class.forName("com.mysql.cj.jdbc.Driver");
        var driver = (Driver) aClass.getDeclaredConstructor().newInstance();
        DriverManager.registerDriver(driver);
        var url = "jdbc:mysql://localhost:3306/test";
        var user = "root";
        var password = "tkl19981125";

        var cxn = DriverManager.getConnection(url, user, password);
        System.out.println(cxn);
    }

    /**
     * 利用MySQL的Driver类的静态代码块
     */
    @Test
    public void testCxn4() throws ClassNotFoundException, SQLException {
        //  因为mysql-connector-java-xxx.jar的META-INF的services下有类路径，此语句也可省，但不通用
        Class.forName("com.mysql.cj.jdbc.Driver");
        var url = "jdbc:mysql://localhost:3306/test";
        var user = "root";
        var password = "tkl19981125";
        var cxn = DriverManager.getConnection(url, user, password);
        System.out.println(cxn);
    }

    /**
     * <p>最终版</p>
     * <p>避免硬编码</p>
     * <p>好处</p>
     * <ol>
     *     <li>分离数据与代码</li>
     *     <li>修改配置信息时无需重新打包📦</li>
     * </ol>
     */
    @Test
    public void testCxnUltimate() throws IOException, ClassNotFoundException, SQLException {
        var in = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("jdbc.properties");
        var prop = new Properties();
        prop.load(in);
        var url = prop.getProperty("url");
        var user = prop.getProperty("user");
        var password = prop.getProperty("password");
        var driverClass = prop.getProperty("driverClass");

        Class.forName(driverClass);
        var cxn = DriverManager.getConnection(url, user, password);
        System.out.println(cxn);
    }
}
