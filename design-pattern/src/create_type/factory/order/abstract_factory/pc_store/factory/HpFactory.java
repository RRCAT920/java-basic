package create_type.factory.order.abstract_factory.pc_store.factory;

import create_type.factory.order.abstract_factory.pc_store.product.HpKeyboard;
import create_type.factory.order.abstract_factory.pc_store.product.HpMouse;
import create_type.factory.order.abstract_factory.pc_store.product.Keyboard;
import create_type.factory.order.abstract_factory.pc_store.product.Mouse;

/**
 * @author huzihao
 * @since 2020/11/5 20:09
 */
public class HpFactory implements PcFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }

    @Override
    public Keyboard createKeyboard() {
        return new HpKeyboard();
    }
}
