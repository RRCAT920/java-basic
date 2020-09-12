package reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author huzihao
 * @since 2020/9/13 02:18
 */
public class ProxyFactory {
    /**
     * 获得代理类对象
     * @param o 被代理类的对象
     * @return 代理类的对象
     */
    public static Object getProxyOf(final Object o) {
        var handler = new InvocationHandler() {
            /**
             * 调用代理的同名方法时会调用此方法，故将代理逻辑写入此方法中。
             * @param proxy 代理
             * @param method 同名方法
             * @param args 同名方法的参数
             * @return 同名方法的返回值
             * @throws Throwable ...
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                HumanUtil.method1();
                var value = method.invoke(o, args);
                HumanUtil.method2();
                return value;
            }
        };
        return Proxy.newProxyInstance(
                o.getClass().getClassLoader(),
                o.getClass().getInterfaces(),
                handler);
    }
}
