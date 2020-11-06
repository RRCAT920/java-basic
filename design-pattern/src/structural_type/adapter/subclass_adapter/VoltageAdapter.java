package structural_type.adapter.subclass_adapter;

/**
 * @author huzihao
 * @since 2020/11/2 16:30
 */
public class VoltageAdapter extends Voltage200 implements Voltage5 {
    @Override
    public int output5V() {
        int srcV = output200V();
        return srcV / 40;
    }
}
