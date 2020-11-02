package create_type.singleton.static_inner_class;

/**
 * @author huzihao
 * @since 2020/10/30 16:50
 */
public class Singleton {
    /**
     * Don't let anyone to instantiate this class
     */
    private Singleton() {
    }

    private static class Instance {
        public static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return Instance.instance;
    }

    public static void main(String[] args) {
        var s1 = Singleton.getInstance();
        var s2 = Singleton.getInstance();
        //noinspection ConstantConditions
        assert s1 == s2;
    }
}
