package structural_type.decorator.car;

/**
 * @author huzihao
 * @since 2020/11/9 15:44
 */
public class SportsCar extends CarDecorator {
    public SportsCar(Car car) {
        super(car);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding features of Sports Car.");
    }
}
