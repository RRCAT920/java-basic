package structural_type.proxy.dynamic_proxy.subclass_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author huzihao
 * @since 2020/11/3 00:15
 */
public class ProxyFactory implements MethodInterceptor {
    private final Object target;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    public Object getProxyInstance() {
        /*
        实例化工具类
        设置父类
        设置回调方法(实现类即可)
        创建代理对象
         */
        var enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy)
            throws Throwable {
        System.out.println("cglib代理开始");
        var returnVal = method.invoke(target, args);
        System.out.println("cglib代理结束");
        return returnVal;
    }
}
