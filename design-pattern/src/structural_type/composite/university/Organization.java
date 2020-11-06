package structural_type.composite.university;

/**
 * @author huzihao
 * @since 2020/11/2 19:13
 */
public abstract class Organization {
    private final String name;

    protected Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(Organization organization) {
        throw new UnsupportedOperationException();
    }

    public void remove(Organization organization) {
        throw new UnsupportedOperationException();
    }

    public abstract void print();
}
