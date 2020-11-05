package create_type.factory.order.abstract_factory.pc_store.factory;

import create_type.factory.order.abstract_factory.pc_store.product.DellKeyboard;
import create_type.factory.order.abstract_factory.pc_store.product.DellMouse;
import create_type.factory.order.abstract_factory.pc_store.product.Keyboard;
import create_type.factory.order.abstract_factory.pc_store.product.Mouse;

/**
 * @author huzihao
 * @since 2020/11/5 20:09
 */
public class DellFactory implements PcFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new DellKeyboard();
    }
}
