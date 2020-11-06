package structural_type.adapter;

/**
 * @author huzihao
 * @since 2020/11/6 12:09
 */
public class AbstractSocketAdapter implements SocketAdapter {
    @Override
    public Volt get120Volt() {
        return Volt.EMPTY_VOLT;
    }

    @Override
    public Volt get12Volt() {
        return Volt.EMPTY_VOLT;
    }

    @Override
    public Volt get3Volt() {
        return Volt.EMPTY_VOLT;
    }
}
