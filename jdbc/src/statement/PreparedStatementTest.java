package statement;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;

import util.JDBCUtil;

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
        try (var cxn = JDBCUtil.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.setString(1, "哪吒");
            stmt.setString(2, "nezha@gmail.com");
            stmt.setDate(3,
                    Date.valueOf(LocalDate.of(1000, 1, 1)));

            stmt.execute();
        } catch (IOException | ClassNotFoundException | SQLException e) {
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
        try (var cxn = JDBCUtil.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.setObject(1, "莫扎特");
            stmt.setObject(2, 18);
            stmt.execute();
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void delete() {
        var sql = "delete from customers where id = ?";
        JDBCUtil.execute(sql, Collections.singletonList(3));
    }

    @Test
    public void testNonMatch() {
        var sql = "update customers set name = ? where id = ?";
        JDBCUtil.execute(sql, Collections.singletonList("莫扎特"));
    }

    @Test
    public void specificQuery() {

    }
}
