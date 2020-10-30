package factory.pizza;

/**
 * @author huzihao
 * @since 2020/10/30 17:38
 */
public class CheesePizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备芝士披萨");
    }
}
