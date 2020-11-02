package decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:27
 */
public abstract class Drink {
    public abstract double cost();

    private String description;
    private double price;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
