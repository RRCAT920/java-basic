package behavioral_type.iterator;

import java.util.Iterator;

/**
 * @author huzihao
 * @since 2020/11/3 20:42
 */
public interface College {
    Iterator<Department> iterator();

    String getName();

    void addDepartment(String name, String description);
}
