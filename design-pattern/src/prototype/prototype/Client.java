package prototype.prototype;

/**
 * @author huzihao
 * @since 2020/10/31 21:24
 */
public class Client {
    public static void main(String[] args) {
        var sheep = new Sheep("tom", 1, "white");
        System.out.println(sheep);
        try {
            var sheep1 = (Sheep) sheep.clone();
            var sheep2 = (Sheep) sheep.clone();
            var sheep3 = (Sheep) sheep.clone();
            System.out.println(sheep1);
            System.out.println(sheep2);
            System.out.println(sheep3);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
