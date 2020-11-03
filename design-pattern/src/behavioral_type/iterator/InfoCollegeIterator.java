package behavioral_type.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/3 20:38
 */
public class InfoCollegeIterator implements Iterator<Department> {
    private final List<Department> departmentList;
    private int index = 0;

    public InfoCollegeIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    @Override
    public boolean hasNext() {
        return index < departmentList.size();
    }

    @Override
    public Department next() {
        return departmentList.get(index++);
    }
}
