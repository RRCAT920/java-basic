package dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dao.CustomerDAO;
import dao.DAO;
import pojo.Customer;

/**
 * @author huzihao
 * @since 2020/10/11 22:27
 */
public class CustomerDAOImpl extends DAO implements CustomerDAO {
    @Override
    public void insert(Connection connection, Customer customer) {
        var sql = """
                insert into customers(name,email,birth)
                values(?,?,?)
                """;
        update(connection, sql, 
                Arrays.asList(customer.getName(), customer.getEmail(), customer.getBirth()));
    }

    @Override
    public void deleteById(Connection connection, int id) {
        var sql = "delete from customers where id = ?";
        update(connection, sql, Collections.singletonList(id));
    }

    @Override
    public void update(Connection connection, Customer customer) {
        var sql = """
                update customers
                set name = ?, email = ?, birth = ?
                where id = ?
                """;
        update(connection, sql, Arrays.asList(customer.getName(), customer.getEmail(),
                customer.getBirth(), customer.getId()));
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) {
        var sql = """
                select id, name, email, birth
                from customers
                where id = ?
                """;
        return get(connection, Customer.class, sql, Collections.singletonList(id));
    }

    @Override
    public List<Customer> getAll(Connection connection) {
        var sql = """
                select id, name, email, birth
                from customers
                """;
        return getList(connection, Customer.class, sql, null).orElse(null);
    }

    @Override
    public Long getCount(Connection connection) {
        var sql = "select count(*) from customers";
        return getValue(connection, sql, null);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        var sql = """
                select max(birth)
                from customers
                """;
        return getValue(connection, sql, null);
    }
}
