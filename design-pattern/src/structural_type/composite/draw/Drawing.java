package structural_type.composite.draw;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/6 14:11
 */
public class Drawing implements Shape {
    private final List<Shape> shapeList = new ArrayList<>();

    public void add(Shape shape) {
        shapeList.add(shape);
    }

    public void remove(Shape shape) {
        shapeList.remove(shape);
    }

    public void clear() {
        System.out.println("Clearing all the shapes from drawing");
        shapeList.clear();
    }

    @Override
    public void draw(String color) {
        shapeList.forEach(shape -> shape.draw(color));
    }
}
