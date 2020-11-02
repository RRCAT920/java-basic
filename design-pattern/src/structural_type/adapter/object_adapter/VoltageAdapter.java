package structural_type.adapter.object_adapter;

/**
 * @author huzihao
 * @since 2020/11/2 16:30
 */
public class VoltageAdapter implements Voltage5 {
    private final Voltage200 voltage200;

    public VoltageAdapter(Voltage200 voltage200) {
        this.voltage200 = voltage200;
    }

    @Override
    public int output5V() {
        if (null != voltage200) {
            return voltage200.output200V() / 40;
        }
        return 0;
    }
}
