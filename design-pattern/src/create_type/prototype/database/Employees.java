package create_type.prototype.database;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/5 22:42
 */
public class Employees implements Cloneable {
    private final List<String> employeeList;

    public Employees() {
        employeeList = new ArrayList<>();
    }

    public Employees(List<String> employeeList) {
        this.employeeList = employeeList;
    }

    public void loadData() {
        // 模拟从数据库总读取数据
        employeeList.add("张三");
        employeeList.add("李四");
        employeeList.add("王五");
        employeeList.add("赵六");
        employeeList.add("赵子龙");
    }

    public List<String> getEmployeeList() {
        return employeeList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Employees(new ArrayList<>(this.employeeList));
    }

    @Override
    public String toString() {
        return "Employees{" +
                "employeeList=" + employeeList +
                '}';
    }
}
