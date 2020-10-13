package apache_dbutils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

/**
 * @author huzihao
 * @since 2020/10/13 22:01
 */
public class QueryRunnerTest {
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
    void insert() {
        var queryRunner = new QueryRunner(dataSource);
        var sql = """
                insert into customers(name, email, birth)
                values(?, ?, ?)
                """;
        try {
            var affectedRowNumber = queryRunner.update(sql,
                    "李容蓉", "lrr@lrr.com", "1888-01-01");
            System.out.println(affectedRowNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
