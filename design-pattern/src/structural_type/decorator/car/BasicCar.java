package structural_type.decorator.car;

/**
 * @author huzihao
 * @since 2020/11/9 15:43
 */
public class BasicCar implements Car {
    @Override
    public void assemble() {
        System.out.println("Basic Car.");
    }
}
