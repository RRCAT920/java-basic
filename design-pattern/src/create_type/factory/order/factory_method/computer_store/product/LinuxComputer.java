package create_type.factory.order.factory_method.computer_store.product;

/**
 * @author huzihao
 * @since 2020/11/5 18:22
 */
public class LinuxComputer extends Computer {
    public LinuxComputer(String ram, String hhd, String cpu, String osVersion) {
        super(ram, hhd, cpu, "Linux", osVersion);
    }
}
