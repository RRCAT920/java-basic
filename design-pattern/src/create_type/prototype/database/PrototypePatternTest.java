package create_type.prototype.database;

/**
 * 假设有一个从数据库中加载数据的对象，现在要对数据进行多次获取和修改，如果每次获取都要从数据库中读取则十分费时。
 *
 * @author huzihao
 * @since 2020/11/5 22:38
 */
public class PrototypePatternTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        var employees = new Employees();
        employees.loadData();

        // 只要赵姓
        var zhaoEmps = (Employees) employees.clone();
        var zhaoEmpList = zhaoEmps.getEmployeeList();
        zhaoEmpList.removeIf(s -> !s.startsWith("赵"));
        System.out.println(zhaoEmps);

        // 不要赵姓
        var noneZhaoEmps = (Employees) employees.clone();
        var noneZhaoEmpList = noneZhaoEmps.getEmployeeList();
        noneZhaoEmpList.removeIf(s -> s.startsWith("赵"));
        System.out.println(noneZhaoEmps);
    }
}
