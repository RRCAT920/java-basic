package structural_type.proxy.security.static_proxy;

/**
 * @author huzihao
 * @since 2020/11/8 22:20
 */
public class CommandExecutorProxy implements CommandExecutor {
    private boolean isAdmin;
    private final CommandExecutor executor = new CommandExecutorImpl();

    public CommandExecutorProxy(String username, String password) {
        if ("admin".equals(username) && "root".equals(password)) {
            isAdmin = true;
        }
    }

    @Override
    public void runCommand(String cmd) throws Exception {
        if (!isAdmin && cmd.strip().startsWith("rm")) {
            throw new Exception("rm command is not allowed to non-admin users.");
        }
        executor.runCommand(cmd);
    }
}
