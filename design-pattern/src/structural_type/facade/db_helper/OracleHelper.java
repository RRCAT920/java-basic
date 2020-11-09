package structural_type.facade.db_helper;

import java.sql.Connection;

/**
 * @author huzihao
 * @since 2020/11/9 13:29
 */
public class OracleHelper {
    public static Connection getOracleConnection() {
        System.out.println("Oracle:获取连接");
        return null;
    }

    public static void generateOraclePdfReport(String tableName, Connection connection) {
        System.out.println("Oracle:打印" + tableName + "的pdf报告");
    }

    public static void generateOracleHtmlReport(String tableName, Connection connection) {
        System.out.println("Oracle:打印" + tableName + "的html报告");
    }
}
