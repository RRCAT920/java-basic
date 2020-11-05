package create_type.factory.order.factory_method.computer_store.factory;

import create_type.factory.order.factory_method.computer_store.product.Computer;

/**
 * @author huzihao
 * @since 2020/11/5 18:25
 */
public interface ComputerFactory {
    Computer getComputer(String type, String ram, String hhd, String cpu, String osVersion);
}
