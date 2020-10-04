package statement;

import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import record.Customer;
import record.Order;
import util.JDBCUtils;
import util.StringUtils;

/**
 * @author huzihao
 * @since 2020/9/24 21:02
 */
public class PreparedStatementTest {
    private static final Scanner in = new Scanner(System.in);
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
        // 已经预编译，不能进行SQL注入
        var sql = "select user, password from user_table where user = ? and password = ?";
        try (final var cxn = JDBCUtils.getConnection();
             final var stmt = cxn.prepareStatement(sql)) {
            stmt.setString(1, "1 or 1='");
            stmt.setString(2, "' or 1 = 1");
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
        final var customers = JDBCUtils.query(Customer.class);
        System.out.println(customers);
    }

    public static void main(String[] args) {
        queryExamStudent();
    }

    /**
     * 从控制台向数据库的表customers中插入一条数据
     */
    public static void userInput() {
        System.out.print("请输入姓名：");
        final var name = in.next();
        System.out.print("请输入邮箱：");
        final var email = in.next();
        System.out.print("请输入生日(YYYY MM dd)：");
        final var birth = Date.valueOf(LocalDate.of(in.nextInt(), in.nextInt(), in.nextInt()));
        var sql = "insert into customers(name, email, birth) values(?, ?, ?)";
        JDBCUtils.execute(sql, Arrays.asList(name, email, birth));
    }

    public static void insertExamStudent() {
        System.out.print("Type: ");
        final var type = in.nextInt();
        System.out.print("IDCard: ");
        final var idCard = in.next();
        System.out.print("ExamCard: ");
        final var examCard = in.next();
        System.out.print("StudentName: ");
        final var studentName = in.next();
        System.out.print("Location: ");
        final var location = in.next();
        System.out.print("Grade: ");
        final var grade = in.nextInt();
        final var sql = "insert into exam_student(Type,IDCard,ExamCard,StudentName,Location,Grade)" +
                " values(?,?,?,?,?,?)";
        JDBCUtils.execute(sql, Arrays.asList(type, idCard, examCard, studentName, location, grade));
        System.out.println("信息录入成功！");
    }

    public static void queryExamStudent() {
        System.out.println("请选择您要输入的类型：");
        System.out.println("a:准考证号");
        System.out.println("b:身份证号");
        var sql = "select FlowID flowId, Type type, IDCard idCard, ExamCard examCard, StudentName studentName, Location location, Grade grade from exam_student where %s = ?";

        switch (in.next()) {
            case "a" -> {
                System.out.println("请输入准考证号：");
                sql = sql.formatted("ExamCard");
            }
            case "b" -> {
                System.out.println("请输入身份证号: ");
                sql = sql.formatted("IDCard");
            }
            case "c" -> {
                System.out.println("您的输入有误，请重新进入程序");
                return;
            }
        }

        var students = JDBCUtils
                .query(ExamStudent.class, sql, Collections.singletonList(in.next()))
                .orElse(null);
        if (null != students) {
            System.out.println("========查询结果========");
            var student = students.get(0);
            System.out.printf("%-6s%d%n", "流水号:", student.getFlowId());
            System.out.printf("%-6s%d%n", "四级/六级:", student.getType());
            System.out.printf("%-6s%s%n", "身份证号:", student.getIdCard());
            System.out.printf("%-6s%s%n", "准考证号:", student.getExamCard());
            System.out.printf("%-6s%s%n", "学生姓名:", student.getStudentName());
            System.out.printf("%-6s%s%n", "区域:", student.getLocation());
            System.out.printf("%-6s%d%n", "成绩:", student.getGrade());
        } else {
            System.out.println("查无此人，请重新进入程序");
        }
    }
}
