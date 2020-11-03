package behavioral_type.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/3 20:45
 */
public class InfoCollege implements College {
    private final List<Department> departmentList;

    public InfoCollege() {
        departmentList = new ArrayList<>();
        addDepartment("信息安全专业", "信息安全");
        addDepartment("网络安全专业", "网络安全");
        addDepartment("服务器安全专业", "服务器安全");
    }

    @Override
    public Iterator<Department> iterator() {
        return new InfoCollegeIterator(departmentList);
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String description) {
        departmentList.add(new Department(name, description));
    }
}
