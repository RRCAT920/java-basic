package structural_type.adapter;

import org.junit.Test;

import structural_type.adapter.std.SocketClassAdapterImpl;
import structural_type.adapter.std.SocketInterfaceAdapterImpl;
import structural_type.adapter.std.SocketObjectAdapterImpl;

/**
 * @author huzihao
 * @since 2020/11/6 12:02
 */
public class AdapterPatternTest {
    @Test
    public void testClassAdapter() {
        var socketClassAdapter = new SocketClassAdapterImpl();
        print(socketClassAdapter);
    }

    @Test
    public void testObjectAdapter() {
        var socketObjectAdapter = new SocketObjectAdapterImpl();
        print(socketObjectAdapter);
    }

    @Test
    public void testInterfaceAdapter() {
        var socketInterfaceAdapter = new SocketInterfaceAdapterImpl();
        print(socketInterfaceAdapter);
    }

    private void print(SocketAdapter adapter) {
        var v3 = getVolt(adapter, 3);
        var v12 = getVolt(adapter, 12);
        var v200 = getVolt(adapter, 200);

        System.out.println(v3.getVolts());
        System.out.println(v12.getVolts());
        System.out.println(v200.getVolts());
    }

    public static Volt getVolt(SocketAdapter socketAdapter, int volts) {
        return switch (volts) {
            case 3 -> socketAdapter.get3Volt();
            case 12 -> socketAdapter.get12Volt();
            default -> socketAdapter.get120Volt();
        };
    }
}
