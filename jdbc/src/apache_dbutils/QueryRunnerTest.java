package apache_dbutils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import pojo.Customer;

/**
 * @author huzihao
 * @since 2020/10/13 22:01
 */
public class QueryRunnerTest {
    private static QueryRunner queryRunner;

    static {
        try (var in = ClassLoader.getSystemClassLoader()
                .getResourceAsStream("druid.properties")) {
            var props = new Properties();
            props.load(in);
            DataSource dataSource = DruidDataSourceFactory.createDataSource(props);
            queryRunner = new QueryRunner(dataSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void insert() {
        try {
            var sql = """
                    insert into customers(name, email, birth)
                    values(?, ?, ?)
                    """;
            var affectedRowNumber = queryRunner.update(sql,
                    "李容蓉", "lrr@lrr.com", "1888-01-01");
            System.out.println(affectedRowNumber);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // BeanHandler是ResultSetHandler的实现类，用于封装表中的一条记录。
    @Test
    void queryCustomer() {
        try {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id = ?
                    """;
            var beanHandler = new BeanHandler<>(Customer.class);
            var customer = queryRunner.query(sql, beanHandler, 22);
            System.out.println(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void queryCustomerList() {
        try {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id < ?               
                    """;
            var beanListHandler = new BeanListHandler<>(Customer.class);
            var customerList = queryRunner.query(sql, beanListHandler, 22);
            customerList.forEach(System.out::println);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void queryMap() {
        try {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id < ?               
                    """;
            var mapHandler = new MapHandler();
            var map = queryRunner.query(sql, mapHandler, 22);
            System.out.println(map);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void queryMapList() {
        try {
            var sql = """
                    select id, name, email, birth
                    from customers
                    where id < ?               
                    """;
            var mapListHandler = new MapListHandler();
            var mapList = queryRunner.query(sql, mapListHandler, 22);
            System.out.println(mapList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
