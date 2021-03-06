package structural_type.proxy.security.jdk_proxy;

/**
 * @author huzihao
 * @since 2020/11/8 22:35
 */
public class CommandExecutorImpl implements CommandExecutor {
    @Override
    public void runCommand(String cmd) throws Exception {
        Runtime.getRuntime().exec(cmd);
        System.out.printf("'%s' command executed.%n", cmd);
    }
}
