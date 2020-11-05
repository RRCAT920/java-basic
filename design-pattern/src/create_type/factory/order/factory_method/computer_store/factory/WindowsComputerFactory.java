package create_type.factory.order.factory_method.computer_store.factory;

import create_type.factory.order.factory_method.computer_store.product.Computer;
import create_type.factory.order.factory_method.computer_store.product.WindowsPC;
import create_type.factory.order.factory_method.computer_store.product.WindowsServer;

/**
 * @author huzihao
 * @since 2020/11/5 18:32
 */
public class WindowsComputerFactory implements ComputerFactory {
    @Override
    public Computer getComputer(String type, String ram, String hhd, String cpu, String osVersion) {
        return switch (type.toLowerCase()) {
            case "pc" -> new WindowsPC(ram, hhd, cpu, osVersion);
            case "server" -> new WindowsServer(ram, hhd, cpu, osVersion);
            default -> throw new IllegalStateException("Unexpected value: " + type.toLowerCase());
        };
    }
}
