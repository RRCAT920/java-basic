package structural_type.bridge;

/**
 * @author huzihao
 * @since 2020/11/2 17:45
 */
public class FoldedPhone extends Phone {
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    @Override
    protected void open() {
        super.open();
        System.out.println("翻盖手机开机");
    }

    @Override
    protected void close() {
        super.close();
        System.out.println("翻盖手机关机");
    }

    @Override
    protected void call() {
        super.call();
        System.out.println("翻盖手机打电话");
    }
}
