package structural_type.composite.university;

/**
 * @author huzihao
 * @since 2020/11/2 19:19
 */
public class Department extends Organization {
    public Department(String name) {
        super(name);
    }

    @Override
    public void print() {
        System.out.println(getName());
    }
}
