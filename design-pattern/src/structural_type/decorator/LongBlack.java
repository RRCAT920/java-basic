package structural_type.decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:29
 */
public class LongBlack extends Drink {
    public LongBlack() {
        setDescription("美式咖啡");
        setPrice(15.0);
    }

    @Override
    public double cost() {
        // 手工费
        return getPrice() + 2.0;
    }
}
