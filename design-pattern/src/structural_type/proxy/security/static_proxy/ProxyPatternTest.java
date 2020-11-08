package structural_type.proxy.security.static_proxy;

/**
 * @author huzihao
 * @since 2020/11/8 22:25
 */
public class ProxyPatternTest {
    public static void main(String[] args) {
        CommandExecutor proxy = new CommandExecutorProxy("admin", "wrong_password");
        try {
            proxy.runCommand("ls -l");
            proxy.runCommand("rm -rf *");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
