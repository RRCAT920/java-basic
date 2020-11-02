package structural_type.decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:28
 */
public class Decaf extends Drink {
    public Decaf() {
        setDescription("无因咖啡");
        setPrice(20.0);
    }

    @Override
    public double cost() {
        // 过滤费
        return getPrice() + 10.0;
    }
}
