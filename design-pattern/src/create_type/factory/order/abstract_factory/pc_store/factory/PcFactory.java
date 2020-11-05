package create_type.factory.order.abstract_factory.pc_store.factory;

import create_type.factory.order.abstract_factory.pc_store.product.Keyboard;
import create_type.factory.order.abstract_factory.pc_store.product.Mouse;

/**
 * @author huzihao
 * @since 2020/11/5 20:06
 */
public interface PcFactory {
    Mouse createMouse();

    Keyboard createKeyboard();
}
