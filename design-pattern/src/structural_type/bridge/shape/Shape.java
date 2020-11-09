package structural_type.bridge.shape;

/**
 * @author huzihao
 * @since 2020/11/9 15:23
 */
public abstract class Shape {
    protected final Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void applyColor();
}
