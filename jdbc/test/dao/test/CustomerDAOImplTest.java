package dao.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import dao.impl.CustomerDAOImpl;
import pojo.Customer;
import util.DBUtils;

/**
 * @author huzihao
 * @since 2020/10/11 22:55
 */
class CustomerDAOImplTest {
    private static final CustomerDAOImpl dao = new CustomerDAOImpl();
    private static Connection cxn;

    @BeforeAll
    static void connect() {
        try {
            cxn = DBUtils.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterAll
    static void close() {
        DBUtils.closeResources(cxn);
    }

    @Test
    void insert() {
        try (var cxn = DBUtils.getConnection()) {
            var customer = new Customer("李容蓉", "lirongrong@13213.com",
                    Date.valueOf(LocalDate.now()));
            dao.insert(cxn, customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    void deleteById() {
        dao.deleteById(cxn, 23);
    }

    @Test
    void update() {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var temporalAccessor = formatter.parse("1988-09-20");
        var customer = new Customer(24, "李容蓉猪猪", "zhuzhu@123.com",
                Date.valueOf(LocalDate.from(temporalAccessor)));
        dao.update(cxn, customer);
    }

    @Test
    void getCustomerById() {
        var customer = dao.getCustomerById(cxn, 1);
        System.out.println(customer);
    }

    @Test
    void getAll() {
        var customers = dao.getAll(cxn);
        customers.forEach(System.out::println);
    }

    @Test
    void getCount() {
        var count = dao.getCount(cxn);
        System.out.println(count);
    }

    @Test
    void getMaxBirth() {
        var maxBirth = dao.getMaxBirth(cxn);
        System.out.println(maxBirth);
    }
}