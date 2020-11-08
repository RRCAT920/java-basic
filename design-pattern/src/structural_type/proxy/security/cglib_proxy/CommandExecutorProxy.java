package structural_type.proxy.security.cglib_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author huzihao
 * @since 2020/11/8 22:47
 */
public class CommandExecutorProxy implements MethodInterceptor {
    private final Object target;
    private final boolean isAdmin;

    public CommandExecutorProxy(Object target, String username, String password) {
        this.target = target;
        isAdmin = "admin".equals(username) && "root".equals(password);
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        if (!isAdmin && args[0].toString().strip().startsWith("rm")) {
            throw new Exception("rm command is not allowed for non-admin users.");
        }
        return method.invoke(target, args);
    }

    public Object getProxy() {
        var enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }
}
