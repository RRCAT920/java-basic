package dao_opt;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import pojo.Customer;

/**
 * @author huzihao
 * @since 2020/10/11 22:23
 */
public interface CustomerDAO {
    void insert(Connection connection, Customer customer);

    void deleteById(Connection connection, int id);

    void update(Connection connection, Customer customer);

    Customer getCustomerById(Connection connection, int id);

    List<Customer> getAll(Connection connection);

    /**
     * 获得表的行数
     * @param connection 数据库连接
     * @return 行数
     */
    Long getCount(Connection connection);

    Date getMaxBirth(Connection connection);
}
