package structural_type.adapter;

/**
 * @author huzihao
 * @since 2020/11/6 11:58
 */
public interface SocketAdapter {
    Volt get120Volt();

    Volt get12Volt();

    Volt get3Volt();
}
