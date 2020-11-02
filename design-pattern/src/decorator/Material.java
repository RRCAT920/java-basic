package decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:30
 */
public class Material extends Drink {
    private final Drink drink;

    public Material(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " " + drink.getDescription();
    }

    @Override
    public double cost() {
        return getPrice() + drink.cost();
    }
}
