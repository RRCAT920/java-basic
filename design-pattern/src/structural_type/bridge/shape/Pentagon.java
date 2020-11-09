package structural_type.bridge.shape;

/**
 * @author huzihao
 * @since 2020/11/9 15:24
 */
public class Pentagon extends Shape {
    public Pentagon(Color color) {
        super(color);
    }

    @Override
    public void applyColor() {
        System.out.print("Pentagon filled with color ");
        color.applyColor();
    }
}
