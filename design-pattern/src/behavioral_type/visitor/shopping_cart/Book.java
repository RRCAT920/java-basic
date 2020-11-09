package behavioral_type.visitor.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 21:46
 */
public class Book implements Item {
    private int price;
    private String isbn;

    public Book(int price, String isbn) {
        this.price = price;
        this.isbn = isbn;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public int calculatePrice(ItemPriceCalculator shoppingCart) {
        return shoppingCart.calculatePrice(this);
    }
}
