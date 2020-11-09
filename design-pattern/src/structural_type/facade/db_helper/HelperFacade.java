package structural_type.facade.db_helper;

import java.sql.Connection;

/**
 * @author huzihao
 * @since 2020/11/9 13:33
 */
public class HelperFacade {
    public static void generateReport(DBType dbType, ReportType reportType, String tableName) {
        Connection connection;
        switch (dbType) {
            case MySQL -> {
                connection = MySqlHelper.getMySqlConnection();
                switch (reportType) {
                    case PDF -> MySqlHelper.generateMySqlPdfReport(tableName, connection);
                    case HTML -> MySqlHelper.generateMySqlHtmlReport(tableName, connection);
                }
            }
            case Oracle -> {
                connection = OracleHelper.getOracleConnection();
                switch (reportType) {
                    case PDF -> OracleHelper.generateOraclePdfReport(tableName, connection);
                    case HTML -> OracleHelper.generateOracleHtmlReport(tableName, connection);
                }
            }
        }
    }

    // 枚举类型作为内部类默认静态
    public enum DBType {
        MySQL,
        Oracle
    }

    public enum ReportType {
        PDF,
        HTML
    }
}
