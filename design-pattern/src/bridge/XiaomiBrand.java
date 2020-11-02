package bridge;

/**
 * @author huzihao
 * @since 2020/11/2 17:43
 */
public class XiaomiBrand implements Brand {
    @Override
    public void open() {
        System.out.println("Xiaomi开机");
    }

    @Override
    public void close() {
        System.out.println("Xiaomi关机");
    }

    @Override
    public void call() {
        System.out.println("小米打电话");
    }
}
