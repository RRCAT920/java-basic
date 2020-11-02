package structural_type.proxy.dynamic_proxy.inteface_proxy;

import java.lang.reflect.Proxy;

/**
 * @author huzihao
 * @since 2020/11/2 23:44
 */
public class ProxyFactory {
    private final Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        // loader：目标对象使用的类加载器
        // interfaces: 目标对象实现的接口
        // h: 执行目标对象的方法时会执行的事件处理器，并且将目标对象执行的方法作为参数传递
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), (proxy, method, args) -> {
                    System.out.println("JDK代理开始");
                    var value = method.invoke(target, args);
                    System.out.println("JDK代理结束");
                    return value;
                });
    }
}
