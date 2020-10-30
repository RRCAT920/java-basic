package factory.pizza;

/**
 * @author huzihao
 * @since 2020/10/30 17:39
 */
public class GreekPizza extends Pizza {
    @Override
    public void prepare() {
        System.out.println("准备希腊披萨");
    }
}
