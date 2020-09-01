package comparable;

/**
 * @author huzihao
 * @since 2020/8/26 21:33
 */
public class Goods implements Comparable<Goods>{
    private String name;
    private double price;

    public Goods(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Goods o) {
        if (Double.compare(price, o.price) != 0) return Double.compare(price, o.price);
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "Goods{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }


    public void testGithub() {
        System.out.println("github");
    }
}

// a test for account
