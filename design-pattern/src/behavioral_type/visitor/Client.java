package behavioral_type.visitor;

/**
 * @author huzihao
 * @since 2020/11/3 16:59
 */
public class Client {
    public static void main(String[] args) {
        var objectStructure = new ObjectStructure();
        objectStructure.add(new Man());
        objectStructure.add(new Man());
        objectStructure.add(new Woman());

        objectStructure.display(new Success());
    }
}
