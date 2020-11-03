package behavioral_type.iterator;

import java.util.Iterator;

/**
 * @author huzihao
 * @since 2020/11/3 20:45
 */
public class ComputerCollege implements College {
    private final Department[] departments;
    int numberOfDepartments = 0;

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("Java", "Java");
        addDepartment("Php", "Php");
        addDepartment("Scala", "Scala");
    }

    @Override
    public Iterator<Department> iterator() {
        return new ComputerCollegeIterator(departments);
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String description) {
        var dept = new Department(name, description);
        departments[numberOfDepartments] = dept;
        numberOfDepartments++;
    }
}
