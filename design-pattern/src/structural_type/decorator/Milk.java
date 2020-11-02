package structural_type.decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:31
 */
public class Milk extends Material {
    public Milk(Drink drink) {
        super(drink);
        setDescription("牛奶");
        setPrice(2.0);
    }
}
