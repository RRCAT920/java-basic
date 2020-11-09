package structural_type.bridge.shape;

/**
 * @author huzihao
 * @since 2020/11/9 15:25
 */
public class Green implements Color {
    @Override
    public void applyColor() {
        System.out.println("green.");
    }
}
