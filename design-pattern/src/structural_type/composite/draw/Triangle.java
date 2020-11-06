package structural_type.composite.draw;

/**
 * @author huzihao
 * @since 2020/11/6 14:10
 */
public class Triangle implements Shape {
    @Override
    public void draw(String color) {
        System.out.println("Drawing Triangle with " + color);
    }
}
