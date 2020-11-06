package structural_type.adapter;

/**
 * Adaptee
 *
 * @author huzihao
 * @since 2020/11/6 11:58
 */
public class Socket {
    public Volt getVolt() {
        return new Volt(120);
    }
}
