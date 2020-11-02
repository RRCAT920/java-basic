package create_type.singleton.static_final_hunger;

/**
 * @author huzihao
 * @since 2020/8/26 23:41
 */
public class Singleton {
    /**
     * Don't let anyone instantiate this class
     */
    private Singleton() {
    }

    // 静态常量饿汉式
    private static final Singleton SINGLETON = new Singleton();

    public static Singleton getInstance() {
        return SINGLETON;
    }

    public static void main(String[] args) {
        var singleton1 = Singleton.getInstance();
        var singleton2 = Singleton.getInstance();
        //noinspection ConstantConditions
        assert singleton1 == singleton2;
    }
}
