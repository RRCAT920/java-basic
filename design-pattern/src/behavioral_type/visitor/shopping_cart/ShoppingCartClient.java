package behavioral_type.visitor.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 21:49
 */
public class ShoppingCartClient {
    public static void main(String[] args) {
        var items = new Item[]{
                new Book(20, "1234"),
                new Book(100, "5678"),
                new Fruit(10, 2, "Banana"),
                new Fruit(5, 5, "Apple")};

        int total = calculateTotal(items);
        System.out.println("Total Cost = " + total);
    }

    private static int calculateTotal(Item[] items) {
        var calculator = new ItemPriceCalculatorImpl();
        int sum = 0;
        for (var item : items) {
            sum += item.calculatePrice(calculator);
        }
        return sum;
    }
}
