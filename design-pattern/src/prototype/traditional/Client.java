package prototype.traditional;

/**
 * @author huzihao
 * @since 2020/10/31 21:12
 */
public class Client {
    public static void main(String[] args) {
        var sheep = new Sheep("tom", 1, "white");
        var sheep2 = new Sheep(sheep.getName(), sheep.getAge(), sheep.getColor());
        //....
        System.out.println(sheep);
        System.out.println(sheep2);
    }
}
