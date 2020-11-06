package structural_type.composite.draw;

/**
 * @author huzihao
 * @since 2020/11/6 14:14
 */
public class CompositePatternTest {
    public static void main(String[] args) {
        var drawing = new Drawing();
        drawing.add(new Triangle());
        drawing.add(new Triangle());
        drawing.add(new Triangle());
        drawing.add(new Circle());
        drawing.add(new Circle());

        drawing.draw("red");

        drawing.clear();
        drawing.add(new Triangle());
        drawing.add(new Circle());
        drawing.draw("green");
    }
}
