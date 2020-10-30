package singleton.double_check;

/**
 * @author huzihao
 * @since 2020/10/30 16:46
 */
public class Singleton {
    private static volatile Singleton instance;

    /**
     * Don't let anyone instantiate this class
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (null == instance) {
            synchronized (Singleton.class) {
                if (null == instance) instance = new Singleton();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        var s1 = Singleton.getInstance();
        var s2 = Singleton.getInstance();
        assert s1 == s2;
    }
}