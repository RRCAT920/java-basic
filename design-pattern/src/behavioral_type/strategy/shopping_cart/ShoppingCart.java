package behavioral_type.strategy.shopping_cart;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/9 20:23
 */
public class ShoppingCart {
    private List<Item> itemList = new ArrayList<>();

    public void add(Item item) {
        itemList.add(item);
    }

    public void remove(Item item) {
        itemList.remove(item);
    }

    public int calculateTotal() {
        return itemList.stream().map(Item::getPrice).reduce(Integer::sum).get();
    }

    public void pay(Payments payments) {
        var total = calculateTotal();
        payments.pay(total);
    }
}
