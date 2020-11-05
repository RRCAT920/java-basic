package create_type.factory.order.factory_method.computer_store.product;

/**
 * @author huzihao
 * @since 2020/11/5 18:23
 */
public class WindowsComputer extends Computer {
    public WindowsComputer(String ram, String hhd, String cpu, String osVersion) {
        super(ram, hhd, cpu, "Windows", osVersion);
    }
}
