package structural_type.proxy.security.static_proxy;

import java.io.IOException;

/**
 * @author huzihao
 * @since 2020/11/8 22:18
 */
public class CommandExecutorImpl implements CommandExecutor {
    @Override
    public void runCommand(String cmd) throws IOException {
        Runtime.getRuntime().exec(cmd);
        System.out.printf("'%s' command executed%n", cmd);
    }
}
