package structural_type.facade.db_helper;

import java.sql.Connection;

/**
 * @author huzihao
 * @since 2020/11/9 13:29
 */
public class MySqlHelper {
    public static Connection getMySqlConnection() {
        System.out.println("MySQL:获取连接");
        return null;
    }

    public static void generateMySqlPdfReport(String tableName, Connection connection) {
        System.out.println("MySQL:打印" + tableName + "的pdf报告");
    }

    public static void generateMySqlHtmlReport(String tableName, Connection connection) {
        System.out.println("MySQL:打印" + tableName + "Html报告");
    }
}
