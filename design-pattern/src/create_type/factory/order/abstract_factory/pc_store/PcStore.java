package create_type.factory.order.abstract_factory.pc_store;

import create_type.factory.order.abstract_factory.pc_store.factory.DellFactory;
import create_type.factory.order.abstract_factory.pc_store.factory.HpFactory;
import create_type.factory.order.abstract_factory.pc_store.factory.PcFactory;

/**
 * @author huzihao
 * @since 2020/11/5 20:10
 */
public class PcStore {
    public static void main(String[] args) {
        PcFactory pcFactory = new HpFactory();

        var hpMouse = pcFactory.createMouse();
        var hpKeyboard = pcFactory.createKeyboard();

        hpMouse.click();
        hpKeyboard.type();

        pcFactory = new DellFactory();
        var dellMouse = pcFactory.createMouse();
        var dellKeyboard = pcFactory.createKeyboard();

        dellMouse.click();
        dellKeyboard.type();
    }
}
