package pool;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import dao_opt.impl.CustomerDAOImpl;

/**
 * @author huzihao
 * @since 2020/10/13 21:14
 */
public class DBCPTest {
    private static DataSource dataSource;

    static {
        try (var fin = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("dbcp.properties")) {
            var props = new Properties();
            props.load(fin);
            dataSource = BasicDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getConnection() throws SQLException {
        var dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true");
        dataSource.setUsername("root");
        dataSource.setPassword("tkl19981125");

        dataSource.setInitialSize(10);

        var cxn = dataSource.getConnection();
        System.out.println(cxn);
    }

    @Test
    void getCxnWithConf() throws Exception {
        var cxn = dataSource.getConnection();
        System.out.println(cxn);
    }

    @Test
    void use() {
        try (var cxn = dataSource.getConnection()) {
            var dao = new CustomerDAOImpl();
            var customer = dao.getCustomerById(cxn, 2);
            System.out.println(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
