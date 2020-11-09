package structural_type.decorator.car;

/**
 * @author huzihao
 * @since 2020/11/9 15:45
 */
public class LuxuryCar extends CarDecorator {
    public LuxuryCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding features of Luxury Car.");
    }
}
