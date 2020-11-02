package structural_type.proxy.dynamic_proxy.subclass_proxy;

/**
 * @author huzihao
 * @since 2020/11/3 00:28
 */
public class Client {
    public static void main(String[] args) {
        var mathTeacher = new MathTeacher();
        var proxyFactory = new ProxyFactory(mathTeacher);
        var proxyTeacher = (MathTeacher) proxyFactory.getProxyInstance();
        proxyTeacher.teach();
    }
}
