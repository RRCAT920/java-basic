package decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:32
 */
public class Chocolate extends Material {
    public Chocolate(Drink drink) {
        super(drink);
        setDescription("巧克力");
        setPrice(4.0);
    }
}
