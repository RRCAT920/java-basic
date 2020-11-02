package structural_type.bridge;

/**
 * @author huzihao
 * @since 2020/11/2 17:46
 */
public class TouchPhone extends Phone {
    public TouchPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("触屏手机开机");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("触屏手机关机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("触屏手机打电话");
    }
}
