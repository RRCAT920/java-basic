package create_type.singleton;

/**
 * @author huzihao
 * @since 2020/11/5 11:05
 */
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton() {
    }

    public static synchronized ThreadSafeSingleton getInstance() {
        if (null == instance) instance = new ThreadSafeSingleton();
        return instance;
    }

    public static ThreadSafeSingleton getInstanceUsingDoubleCheckedLocking() {
        if (null == instance) {
            synchronized (ThreadSafeSingleton.class) {
                if (null == instance) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
