package structural_type.adapter.std;

import structural_type.adapter.AbstractSocketAdapter;
import structural_type.adapter.Socket;
import structural_type.adapter.Volt;

/**
 * @author huzihao
 * @since 2020/11/6 12:11
 */
public class SocketInterfaceAdapterImpl extends AbstractSocketAdapter {
    private final Socket socket = new Socket();

    @Override
    public Volt get12Volt() {
        return convertVolt(socket.getVolt(), 10);
    }

    @Override
    public Volt get3Volt() {
        return convertVolt(socket.getVolt(), 40);
    }

    private Volt convertVolt(Volt volt, int i) {
        return new Volt(volt.getVolts() / i);
    }
}
