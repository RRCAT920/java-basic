package dao_opt.impl;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao_opt.BaseDAO;
import dao_opt.CustomerDAO;
import pojo.Customer;

/**
 * @author huzihao
 * @since 2020/10/11 22:27
 */
public class CustomerDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public void insert(Connection connection, Customer customer) {
        var sql = """
                insert into customers(name,email,birth)
                values(?,?,?)
                """;
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        var sql = "delete from customers where id = ?";
        update(connection, sql, id);
    }

    @Override
    public void update(Connection connection, Customer customer) {
        var sql = """
                update customers
                set name = ?, email = ?, birth = ?
                where id = ?
                """;
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public Customer getCustomerById(Connection connection, int id) {
        var sql = """
                select id, name, email, birth
                from customers
                where id = ?
                """;
        return get(connection, sql, id);
    }

    @Override
    public List<Customer> getAll(Connection connection) {
        var sql = """
                select id, name, email, birth
                from customers
                """;
        return getList(connection, sql).orElse(null);
    }

    @Override
    public Long getCount(Connection connection) {
        var sql = "select count(*) from customers";
        return (Long) getValue(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        var sql = """
                select max(birth)
                from customers
                """;
        return (Date) getValue(connection, sql);
    }
}
