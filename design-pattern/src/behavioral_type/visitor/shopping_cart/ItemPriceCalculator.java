package behavioral_type.visitor.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 21:44
 */
public interface ItemPriceCalculator {
    int calculatePrice(Book book);

    int calculatePrice(Fruit fruit);
}
