import org.junit.Test;

/**
 * @author huzihao
 * @since 2020/8/26 23:56
 */
public class SystemTest {
    /**
     * 测试getProperty(String key)方法
     */
    @Test
    public void testProperty() {
        System.out.println("JRE版本：" + System.getProperty("java.version"));
        System.out.println("Java安装目录：" + System.getProperty("java.home"));
        System.out.println("操作系统名称：" + System.getProperty("os.name"));
        System.out.println("操作系统版本：" + System.getProperty("os.version"));
        System.out.println("用户名：" + System.getProperty("user.name"));
        System.out.println("用户主目录：" + System.getProperty("user.home"));
        System.out.println("用户的当前工作目录：" + System.getProperty("user.dir"));
    }
}
