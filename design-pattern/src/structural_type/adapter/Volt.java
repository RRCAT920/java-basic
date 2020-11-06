package structural_type.adapter;

/**
 * @author huzihao
 * @since 2020/11/6 11:57
 */
public class Volt {
    private final int volts;

    public int getVolts() {
        return volts;
    }

    public Volt(int volts) {
        this.volts = volts;
    }

    public static final Volt EMPTY_VOLT = new Volt(0);
}
