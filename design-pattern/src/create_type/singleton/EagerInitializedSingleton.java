package create_type.singleton;

/**
 * @author huzihao
 * @since 2020/11/5 10:44
 */
public class EagerInitializedSingleton {
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    /**
     * Private constructor to avoid client applications to use constructor
     */
    private EagerInitializedSingleton() {
    }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}
