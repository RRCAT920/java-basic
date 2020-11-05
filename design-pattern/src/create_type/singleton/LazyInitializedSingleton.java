package create_type.singleton;

/**
 * @author huzihao
 * @since 2020/11/5 10:57
 */
public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() {
    }

    public static LazyInitializedSingleton getInstance() {
        if (null == instance) instance = new LazyInitializedSingleton();
        return instance;
    }
}
