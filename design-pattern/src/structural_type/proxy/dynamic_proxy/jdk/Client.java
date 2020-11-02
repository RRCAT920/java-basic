package structural_type.proxy.dynamic_proxy.jdk;

import structural_type.proxy.MathTeacher;
import structural_type.proxy.Teacher;

/**
 * @author huzihao
 * @since 2020/11/2 23:56
 */
public class Client {
    public static void main(String[] args) {
        var target = new MathTeacher();
        var proxyFactory = new ProxyFactory(target);
        var proxy = (Teacher) proxyFactory.getProxyInstance();
        proxy.teach();
    }
}
