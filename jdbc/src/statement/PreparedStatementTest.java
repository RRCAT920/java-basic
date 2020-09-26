package statement;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import record.Customer;
import record.Order;
import util.JDBCUtils;
import util.StringUtils;

/**
 * @author huzihao
 * @since 2020/9/24 21:02
 */
public class PreparedStatementTest {
    /**
     * Statement弊端
     * <ol>
     *     <li>存在SQL拼串</li>
     *     <li>存在SQL注入</li>
     * </ol>
     */
    @Test
    public void insert() {
        var sql = "insert into customers(name,email,birth) values(?,?,?)";
        try (var cxn = JDBCUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.setString(1, "哪吒");
            stmt.setString(2, "nezha@gmail.com");
            stmt.setDate(3,
                    Date.valueOf(LocalDate.of(1000, 1, 1)));

            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * <ol>
     *     <li>获取连接</li>
     *     <li>预编译sql</li>
     *     <li>填充占位符</li>
     *     <li>执行</li>
     *     <li>关闭资源</li>
     * </ol>
     */
    @Test
    public void update() {
        var sql = "update customers set name = ? where id = ?";
        try (var cxn = JDBCUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.setObject(1, "莫扎特");
            stmt.setObject(2, 18);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        var sql = "delete from customers where id = ?";
        JDBCUtils.execute(sql, Collections.singletonList(3));
    }

    @Test
    public void testNonMatch() {
        var sql = "update customers set name = ? where id = ?";
        JDBCUtils.execute(sql, Collections.singletonList("莫扎特"));
    }

    @Test
    public void specificQuery() {
        var sql = "select id, name, email, birth from customers where id = ?";
        try (var cxn = JDBCUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, 1);

            for (var resultSet = stmt.executeQuery(); resultSet.next(); ) {
                var customer = new Customer(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getDate(4));
                System.out.println(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Customer queryForCustomer(String sql, List<Object> holders) {
        Customer customer = null;
        try (var cxn = JDBCUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            for (int i = 0; i < holders.size(); i++) {
                stmt.setObject(i + 1, holders.get(i));
            }

            final var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                customer = new Customer();
                final var metaData = resultSet.getMetaData();
                for (int i = 0; i < metaData.getColumnCount(); i++) {
                    final var columnName = StringUtils.snakeToCamel(metaData.getColumnName(i + 1));
                    final var field = Customer.class.getDeclaredField(columnName);
                    field.setAccessible(true);
                    field.set(customer, resultSet.getObject(i + 1));
                }
            }
        } catch (SQLException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Test
    public void testQueryForCustomer() {
        var sql = "select id, name, email, birth from customers where id = ?";
        System.out.println(queryForCustomer(sql, Collections.singletonList(1)));
    }



    @Test
    public void testSnakeToCamel() {
//        snakeToCamel("_");
//        snakeToCamel("__");
//        snakeToCamel("_name");
//        snakeToCamel("name__");
//        snakeToCamel("__name__");

        assert "orderName".equals(StringUtils.snakeToCamel("order_name"));
        assert "nameA".equals(StringUtils.snakeToCamel("name_a"));
    }

    @Test
    public void sqlInject() {
        // '?'不是占位符，避免SQL注入
        var sql = "select user, password from user_table where user = '?' and password = '?'";
        try (final var cxn = JDBCUtils.getConnection();
             final var stmt = cxn.prepareStatement(sql)) {
            stmt.setString(1, "'1' or '");
            stmt.setString(2, "= 1 or '1' = '1");
            final var resultSet = stmt.executeQuery();
            final var user = resultSet.getString(1);
            final var password = resultSet.getString(2);
            System.out.println(user);
            System.out.println(password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void testQuery() {
        var sql = "select id, name, email, birth from customers where id = ?";
        System.out.println(JDBCUtils.query(Customer.class, sql, Collections.singletonList(1)));
    }

    /**
     * 如果表的列名和类的属性名不一致
     * <ol>
     *     <li>为列起别名</li>
     *     <li>getColumnLabel</li>
     *     <ol>
     *         <li>有别名返回别名</li>
     *         <li>无别名返回列名</li>
     *     </ol>
     * </ol>
     */
    @Test
    public void testQueryOrder() {
        var sql = "select order_id orderId,order_name orderName,order_date orderDate from `order`" +
                " where order_id = ?";
        System.out.println(JDBCUtils.query(Order.class, sql, Collections.singletonList(1)));
    }

    @Test
    public void testNewJDBCUtils() {
        JDBCUtils.setPath("database-test.properties");
        final var customer = JDBCUtils.query(Customer.class);
        System.out.println(customer);
    }

}
