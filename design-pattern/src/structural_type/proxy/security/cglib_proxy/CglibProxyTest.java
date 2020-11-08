package structural_type.proxy.security.cglib_proxy;

/**
 * @author huzihao
 * @since 2020/11/8 22:56
 */
public class CglibProxyTest {
    public static void main(String[] args) {
        var proxyFactory = new CommandExecutorProxy(
                new CommandExecutor(), "admin", "wrong_pwd");
        var commandExecutor = (CommandExecutor) proxyFactory.getProxy();
        try {
            commandExecutor.runCommand("ls -l");
            commandExecutor.runCommand("rm *");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
