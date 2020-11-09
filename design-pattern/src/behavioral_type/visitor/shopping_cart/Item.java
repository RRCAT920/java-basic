package behavioral_type.visitor.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 21:43
 */
public interface Item {
    int calculatePrice(ItemPriceCalculator shoppingCart);
}
