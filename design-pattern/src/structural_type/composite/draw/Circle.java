package structural_type.composite.draw;

/**
 * @author huzihao
 * @since 2020/11/6 14:11
 */
public class Circle implements Shape {
    @Override
    public void draw(String color) {
        System.out.println("Drawing circle with " + color);
    }
}
