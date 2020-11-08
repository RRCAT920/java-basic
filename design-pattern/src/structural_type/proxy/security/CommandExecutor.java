package structural_type.proxy.security;

/**
 * @author huzihao
 * @since 2020/11/8 22:18
 */
public interface CommandExecutor {
    void runCommand(String cmd) throws Exception;
}
