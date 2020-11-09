package behavioral_type.visitor.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 21:48
 */
public class ItemPriceCalculatorImpl implements ItemPriceCalculator {
    @Override
    public int calculatePrice(Book book) {
        var price = book.getPrice();
        return price >= 50 ? price - 5 : price;
    }

    @Override
    public int calculatePrice(Fruit fruit) {
        return fruit.getPricePerKg() * fruit.getWeight();
    }
}
