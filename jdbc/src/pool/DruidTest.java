package pool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import dao_opt.impl.CustomerDAOImpl;

/**
 * @author huzihao
 * @since 2020/10/13 21:38
 */
public class DruidTest {
    private static DataSource dataSource;

    static {
        try (var in = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("druid.properties")) {
            var props = new Properties();
            props.load(in);
            dataSource = DruidDataSourceFactory.createDataSource(props);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void use() {
        try (var cxn = dataSource.getConnection()) {
            var dao = new CustomerDAOImpl();
            var customer = dao.getCustomerById(cxn, 4);
            System.out.println(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
