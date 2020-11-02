package structural_type.bridge;

/**
 * @author huzihao
 * @since 2020/11/2 17:48
 */
public class Client {
    public static void main(String[] args) {
        Phone phone1 = new FoldedPhone(new XiaomiBrand());
        phone1.open();
        phone1.call();
        phone1.close();

        System.out.println("-------------------------");
        phone1 = new TouchPhone(new VivoBrand());
        phone1.open();
        phone1.call();
        phone1.close();
    }
}
