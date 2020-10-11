package api;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.DBUtils;

/**
 * @author huzihao
 * @since 2020/10/4 21:44
 */
public class BatchTest {
    private static final int TIMES = 2000000;
    private static long time = 0;
    private static int count = 0;

    @BeforeEach
    public void init() {
        count++;
        var sql = "truncate table goods";
        try (var cxn = DBUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @AfterEach
    public void conclude() {
        System.out.println(count + ":" + time + "ms");
    }

    @Test
    public void level1() {
        var start = System.currentTimeMillis();
        try (var cxn = DBUtils.getConnection();
             var stmt = cxn.createStatement()) {
            for (var i = 0; i < TIMES; i++) {
                var sql = """
                        insert into goods(name)
                        values('%s')
                        """.formatted("name_" + i);
                stmt.executeUpdate(sql);
            }

            time = System.currentTimeMillis() - start;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void level2() {  // 6s858ms
        var start = System.currentTimeMillis();
        var sql = """
                insert into goods(name)
                values(?)
                """;
        try (var cxn = DBUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            for (var i = 0; i < TIMES; i++) {
                stmt.setString(1, "name_" + i);
                stmt.execute();
            }
            time = System.currentTimeMillis() - start;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    
    @Test
    public void level3() {
        var start = System.currentTimeMillis();
        var sql = """
                insert into goods(name)
                values(?)
                """;
        try (var cxn = DBUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            processBatch(stmt);
            time = System.currentTimeMillis() - start;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void level4() {
        var start = System.currentTimeMillis();
        var sql = """
                insert into goods(name)
                values(?)
                """;
        try (var cxn = DBUtils.getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            cxn.setAutoCommit(false);
            processBatch(stmt);
            cxn.commit();
            time = System.currentTimeMillis() - start;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void processBatch(PreparedStatement stmt) throws SQLException {
        for (var i = 0; i < TIMES; i++) {
            stmt.setString(1, "name_" + i);

            stmt.addBatch();
            if (0 == i % 500 || TIMES - 1 == i) {
                stmt.executeBatch();
                stmt.clearBatch();
            }
        }
    }
}
