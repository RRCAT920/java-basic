package singleton;

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
    
    private static final Singleton SINGLETON = new Singleton();

    public static Singleton getInstance() {
        return SINGLETON;
    }

    @Override
    public String toString() {
        return "This is singleton class.";
    }
}
