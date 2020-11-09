package structural_type.bridge.shape;

/**
 * @author huzihao
 * @since 2020/11/9 15:24
 */
public class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.print("Triangle filled with color ");
        color.applyColor();
    }
}
