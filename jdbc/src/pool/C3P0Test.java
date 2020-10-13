package pool;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.sql.SQLException;


/**
 * @author huzihao
 * @since 2020/10/13 20:09
 */
public class C3P0Test {

    @Test
    public void connectWithHardCode() throws PropertyVetoException, SQLException {
        var pool = new ComboPooledDataSource();
        pool.setDriverClass("com.mysql.cj.jdbc.Driver");
        pool.setJdbcUrl("jdbc:mysql://localhost:3306/test?rewriteBatchedStatements=true");
        pool.setUser("root");
        pool.setPassword("tkl19981125");

        pool.setInitialPoolSize(10);

        var cxn = pool.getConnection();
        System.out.println(cxn);

//        DataSources.destroy(pool);
    }

    @Test
    void connectWithConf() throws SQLException {
        var dataSource = new ComboPooledDataSource("c3p0App");
        var cxn = dataSource.getConnection();
        System.out.println(cxn);
    }
}
