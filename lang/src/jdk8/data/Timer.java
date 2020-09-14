package jdk8.data;

/**
 * @author huzihao
 * @since 2020/9/13 22:36
 */
@FunctionalInterface
public interface Timer<T> {
    T run();

    /**
     * 获得函数运行的时间
     * @return 返回运行时间
     */
    default long time() {
        var start = System.currentTimeMillis();
        run();
        var end = System.currentTimeMillis();
        return end - start;
    }
}
