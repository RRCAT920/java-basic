package structural_type.facade.db_helper;

/**
 * @author huzihao
 * @since 2020/11/9 13:38
 */
public class FacadePatternTest {
    public static void main(String[] args) {
        var tableName = "employee";

        HelperFacade.generateReport(HelperFacade.DBType.MySQL, HelperFacade.ReportType.PDF, tableName);
        HelperFacade.generateReport(HelperFacade.DBType.Oracle, HelperFacade.ReportType.HTML, tableName);
    }
}
