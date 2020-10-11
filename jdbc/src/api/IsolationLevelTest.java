package api;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

import pojo.User;
import util.DBUtils;

/**
 * @author huzihao
 * @since 2020/10/11 21:14
 */
public class IsolationLevelTest {
    @Test
    public void dirtyRead() throws SQLException {
        var cxn = DBUtils.getConnection();
        cxn.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
        cxn.setAutoCommit(false);
        var sql = """
                select user, password, balance
                from user_table
                where user = ? 
                """;
        var users = DBUtils.query(cxn, User.class, sql, "CC").orElse(null);
        if (null != users) {
            var cc = users.get(0);
            System.out.println(cc);
        }
    }

    @Test
    public void update() throws SQLException, InterruptedException {
        var cxn = DBUtils.getConnection();
        cxn.setAutoCommit(false);
        var sql = """
                update user_table
                set balance = ?
                where user = ?
                """;
        DBUtils.execute(cxn, sql, Arrays.asList(5000, "CC"));
        Thread.sleep(15_000);
        System.out.println("修改完成✅");
    }
}
