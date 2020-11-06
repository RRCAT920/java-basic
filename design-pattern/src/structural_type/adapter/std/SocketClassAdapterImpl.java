package structural_type.adapter.std;

import structural_type.adapter.Socket;
import structural_type.adapter.SocketAdapter;
import structural_type.adapter.Volt;

/**
 * @author huzihao
 * @since 2020/11/6 11:59
 */
public class SocketClassAdapterImpl extends Socket implements SocketAdapter {
    @Override
    public Volt get120Volt() {
        return getVolt();
    }

    @Override
    public Volt get12Volt() {
        var volts = getVolt();
        return convertVolt(volts, 10);
    }

    @Override
    public Volt get3Volt() {
        var volts = getVolt();
        return convertVolt(volts, 40);
    }

    private Volt convertVolt(Volt volt, int i) {
        return new Volt(volt.getVolts() / i);
    }
}
