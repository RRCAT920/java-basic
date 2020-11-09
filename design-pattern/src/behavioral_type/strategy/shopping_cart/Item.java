package behavioral_type.strategy.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 20:22
 */
public class Item {
    private String barCode;
    private int price;

    public Item(String barCode, int price) {
        this.barCode = barCode;
        this.price = price;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
