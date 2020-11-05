package create_type.factory.order.factory_method.mouse_store.factory;

import create_type.factory.order.factory_method.mouse_store.mouse.HpMouse;
import create_type.factory.order.factory_method.mouse_store.mouse.Mouse;

/**
 * @author huzihao
 * @since 2020/11/5 19:48
 */
public class HpMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new HpMouse();
    }
}
