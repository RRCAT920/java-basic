package reflection;

/**
 * @author huzihao
 * @since 2020/9/13 03:44
 */
public final class HumanUtil {

    /**
     * 不允许外部创建对象
     */
    private HumanUtil() {
    }

    public static void method1() {
        System.out.println("通用方法1");
    }

    public static void method2() {
        System.out.println("通用方法2");
    }
}
