package behavioral_type.iterator;

import java.util.Iterator;

/**
 * @author huzihao
 * @since 2020/11/3 20:34
 */
public class ComputerCollegeIterator implements Iterator<Department> {
    private final Department[] departments;
    int pos = 0;

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    @Override
    public boolean hasNext() {
        return pos < departments.length && null != departments[pos];
    }

    @Override
    public Department next() {
        return departments[pos++];
    }

    @Override
    public void remove() {

    }
}
