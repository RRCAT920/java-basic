package structural_type.decorator.car;

/**
 * @author huzihao
 * @since 2020/11/9 15:43
 */
public class CarDecorator implements Car {
    protected final Car car;

    public CarDecorator(Car car) {
        this.car = car;
    }

    @Override
    public void assemble() {
        car.assemble();
    }
}
