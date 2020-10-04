package statement;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import static util.JDBCUtils.getConnection;

/**
 * @author huzihao
 * @since 2020/10/4 20:46
 */
public class BlobTest {
    @Test
    public void insertBlobType() {
        var sql = "insert into customers(name,email,birth,photo)values(?,?,?,?)";
        try (var cxn = getConnection();
             var stmt = cxn.prepareStatement(sql);
             var fin = new FileInputStream("nature.jpg")) {
            stmt.setString(1, "徐海强");
            stmt.setString(2, "xhq@126.com");
            stmt.setDate(3, Date.valueOf(LocalDate.now()));
            stmt.setBlob(4, fin);

            stmt.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void updateBlobType() {
        var sql = """
                update customers
                set photo = ?
                where id = ?
                """;
        try (var cxn = getConnection();
             var stmt = cxn.prepareStatement(sql);
             var fin = new FileInputStream("girl.jpeg")) {
            stmt.setBlob(1, fin);
            stmt.setInt(2, 1);
            stmt.execute();
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void queryBlobType() {
        var sql = """
                select photo
                from customers
                where id = ?
                """;
        try (var cxn = getConnection();
             var stmt = cxn.prepareStatement(sql)) {
            stmt.setInt(1, 1);
            try (var resultSet = stmt.executeQuery()) {
                if (resultSet.next()) {
                    try (var in = resultSet.getBlob(1).getBinaryStream();
                         var fout = new FileOutputStream("1.jpg")) {
                        in.transferTo(fout);
                    }
                }
            }
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }
}
