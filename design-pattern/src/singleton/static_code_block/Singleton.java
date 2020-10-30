package singleton.static_code_block;

/**
 * @author huzihao
 * @since 2020/10/30 16:20
 */
public class Singleton {
    private static final Singleton SINGLETON;

    static {
        SINGLETON = new Singleton();
    }

    /**
     * Don't let anyone instantiate this class
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        return SINGLETON;
    }

    public static void main(String[] args) {
        var s1 = Singleton.getInstance();
        var s2 = Singleton.getInstance();
        //noinspection ConstantConditions
        assert s1 == s2;
    }
}
