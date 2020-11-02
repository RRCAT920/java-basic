package composite;

/**
 * @author huzihao
 * @since 2020/11/2 19:13
 */
public abstract class Organization {
    private final String name;

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void add(Organization organization) {
        throw new UnsupportedOperationException();
    }

    protected void remove(Organization organization) {
        throw new UnsupportedOperationException();
    }

    protected abstract void print();
}
