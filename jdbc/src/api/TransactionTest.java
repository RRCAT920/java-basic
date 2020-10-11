package api;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import util.DBUtils;

/**
 * @author huzihao
 * @since 2020/10/11 19:32
 */
public class TransactionTest {
    @Test
    public void carryOver() {
        Connection cxn = null;
        try {
            cxn = DBUtils.getConnection();
            cxn.setAutoCommit(false); // 同时取消DML和连接关闭的自动提交
            var sql1 = """
                    update user_table
                    set balance = balance - 100
                    where user = ?
                    """;
            var sql2 = """
                    update user_table
                    set balance = balance + 100
                    where user = ?
                    """;
            DBUtils.execute(cxn, sql1, "AA");

            System.out.println(Integer.valueOf("asfdasf"));

            DBUtils.execute(cxn, sql2, "BB");
            cxn.commit();
        } catch (SQLException | NumberFormatException throwables) {
            if (null != cxn) {
                try {
                    cxn.rollback();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            throwables.printStackTrace();
        } finally {
            if (null != cxn) {
                try {
                    cxn.setAutoCommit(true);
                    cxn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
