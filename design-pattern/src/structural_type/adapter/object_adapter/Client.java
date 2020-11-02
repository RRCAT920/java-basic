package structural_type.adapter.object_adapter;

/**
 * @author huzihao
 * @since 2020/11/2 16:32
 */
public class Client {
    public static void main(String[] args) {
        var phone = new Phone();
        phone.charging(new VoltageAdapter(new Voltage200()));
    }
}
