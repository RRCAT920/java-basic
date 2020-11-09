package structural_type.decorator.car;

/**
 * @author huzihao
 * @since 2020/11/9 15:45
 */
public class DecoratorPatternTest {
    public static void main(String[] args) {
        var sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();
        System.out.println("---------------");

        var sportsLuxuryCar = new SportsCar(new LuxuryCar(new BasicCar()));
        sportsLuxuryCar.assemble();
    }
}
