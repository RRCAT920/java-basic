package singleton.sync_code_block_lazy;

/**
 * @author huzihao
 * @since 2020/10/30 16:40
 */
public class Singleton {
    private static Singleton instance;

    /**
     * Don't let anyone instantiate this class
     */
    private Singleton() {
    }

    public static Singleton getInstance() {
        if (null == instance) {
            // 错误写法
            synchronized (Singleton.class) {
                instance = new Singleton();
            }
        }
        return instance;
    }
}
