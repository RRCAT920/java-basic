package bridge;

/**
 * @author huzihao
 * @since 2020/11/2 17:43
 */
public class VivoBrand implements Brand {
    @Override
    public void open() {
        System.out.println("Vivo开机");
    }

    @Override
    public void close() {
        System.out.println("Vivo关机");
    }

    @Override
    public void call() {
        System.out.println("Vivo打电话");
    }
}
