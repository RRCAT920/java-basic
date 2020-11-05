package create_type.factory.order.factory_method.computer_store.factory;

import create_type.factory.order.factory_method.computer_store.product.Computer;
import create_type.factory.order.factory_method.computer_store.product.LinuxPC;
import create_type.factory.order.factory_method.computer_store.product.LinuxServer;

/**
 * @author huzihao
 * @since 2020/11/5 18:27
 */
public class LinuxComputerFactory implements ComputerFactory {
    @Override
    public Computer getComputer(String type, String ram, String hhd, String cpu, String osVersion) {
        return switch (type.toLowerCase()) {
            case "pc" -> new LinuxPC(ram, hhd, cpu, osVersion);
            case "server" -> new LinuxServer(ram, hhd, cpu, osVersion);
            default -> throw new IllegalStateException("Unexpected value: " + type.toLowerCase());
        };
    }
}
