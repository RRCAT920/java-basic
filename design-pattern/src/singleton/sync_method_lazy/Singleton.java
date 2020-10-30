package singleton.sync_method_lazy;

/**
 * @author huzihao
 * @since 2020/10/30 16:28
 */
public class Singleton {
    private static Singleton instance;

    /**
     * Don't let anyone instantiate this class
     */
    private Singleton() {
    }

    public static synchronized Singleton getInstance() {
        if (null == instance) {
            instance = new Singleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        var s1 = Singleton.getInstance();
        var s2 = Singleton.getInstance();
        assert s1 == s2;
    }
}
