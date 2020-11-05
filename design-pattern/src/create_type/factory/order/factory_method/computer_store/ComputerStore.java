package create_type.factory.order.factory_method.computer_store;

import create_type.factory.order.factory_method.computer_store.factory.ComputerFactory;
import create_type.factory.order.factory_method.computer_store.factory.LinuxComputerFactory;
import create_type.factory.order.factory_method.computer_store.factory.WindowsComputerFactory;

/**
 * @author huzihao
 * @since 2020/11/5 18:33
 */
public class ComputerStore {
    public static void main(String[] args) {
        ComputerFactory computerFactory = new LinuxComputerFactory();

        var linuxPC = computerFactory
                .getComputer("pc", "2 GB", "500 GB", "2.4 GHz", "7");
        System.out.println(linuxPC);

        var linuxServer = computerFactory
                .getComputer("server", "16 GB", "1 TB", "2.9 GHz", "5");
        System.out.println(linuxServer);

        computerFactory = new WindowsComputerFactory();
        var windowsPC = computerFactory
                .getComputer("pc", "2 GB", "500 GB", "2.4 GHz", "7");
        System.out.println(windowsPC);

        var windowsServer = computerFactory
                .getComputer("server", "16 GB", "1 TB", "2.9 GHz", "5");
        System.out.println(windowsServer);
    }
}
