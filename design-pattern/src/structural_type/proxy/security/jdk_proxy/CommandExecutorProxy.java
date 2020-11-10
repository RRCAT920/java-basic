package structural_type.proxy.security.jdk_proxy;

import java.lang.reflect.Proxy;

/**
 * @author huzihao
 * @since 2020/11/8 22:36
 */
public class CommandExecutorProxy {

    public static Object getProxy(Object target, String username, String password) {
        boolean isAdmin = "admin".equals(username) && "root".equals(password);

        return Proxy.newProxyInstance(CommandExecutorProxy.class.getClassLoader(),
                target.getClass().getInterfaces(), (proxy, method, args) -> {
                    if (!isAdmin && args[0].toString().strip().startsWith("rm")) {
                        throw new Exception("rm command is not allowed for non-admin users.");
                    }
                    return method.invoke(target, args);
                });
    }
}
