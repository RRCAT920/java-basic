package behavioral_type.strategy.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 20:28
 */
public class ShoppingCartTest {
    public static void main(String[] args) {
        var shoppingCart = new ShoppingCart();
        var coco = new Item("123", 20);
        var water = new Item("321", 5);
        shoppingCart.add(coco);
        shoppingCart.add(water);

        shoppingCart.pay(new CreditCard("1234", "1234"));
        shoppingCart.pay(new Wechat("1232456", "123"));
    }
}
