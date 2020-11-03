package behavioral_type.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/3 20:56
 */
public class OutputImpl {
    private final List<College> collegeList;

    public OutputImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    public void printDepartment(Iterator<Department> iterator) {
        while (iterator.hasNext()) {
            var nextEl = iterator.next();
            System.out.println(nextEl.getName());
        }
    }

    public void printCollege() {
        for (var college : collegeList) {
            System.out.println("----" + college.getName() + "----");
            printDepartment(college.iterator());
        }
    }
}
