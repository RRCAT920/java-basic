package bridge;

/**
 * @author huzihao
 * @since 2020/11/2 17:44
 */
public abstract class Phone {
    private final Brand brand;

    public Phone(Brand brand) {
        this.brand = brand;
    }

    protected void open() {
        brand.open();
    }

    protected void close() {
        brand.close();
    }

    protected void call() {
        brand.call();
    }
}
