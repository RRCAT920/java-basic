package dao_opt.impl;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import dao_opt.CustomerDAO;
import util_opt.DBUtils;

/**
 * @author huzihao
 * @since 2020/10/12 20:54
 */
class CustomerDAOImplTest {
    private static final CustomerDAO dao = new CustomerDAOImpl();


    @Test
    void getCustomerById() {
        try (var cxn = DBUtils.getConnection()) {
            var customer = dao.getCustomerById(cxn, 1);
            System.out.println(customer);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}