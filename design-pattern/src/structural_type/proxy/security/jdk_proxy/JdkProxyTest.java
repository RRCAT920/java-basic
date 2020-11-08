package structural_type.proxy.security.jdk_proxy;

/**
 * @author huzihao
 * @since 2020/11/8 22:41
 */
public class JdkProxyTest {
    public static void main(String[] args) {
        var proxy = (CommandExecutor) CommandExecutorProxy.getProxy(
                new CommandExecutorImpl(), "admin", "wrong_pwd");
        try {
            proxy.runCommand("ls");
            proxy.runCommand("rm -rf *");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
