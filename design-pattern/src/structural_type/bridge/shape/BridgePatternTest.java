package structural_type.bridge.shape;

/**
 * @author huzihao
 * @since 2020/11/9 15:25
 */
public class BridgePatternTest {
    public static void main(String[] args) {
        Shape tri = new Triangle(new Red());
        tri.applyColor();

        Shape penta = new Pentagon(new Green());
        penta.applyColor();
    }
}
