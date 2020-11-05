package create_type.factory.order.factory_method.mouse_store.factory;

import create_type.factory.order.factory_method.mouse_store.mouse.DellMouse;
import create_type.factory.order.factory_method.mouse_store.mouse.Mouse;

/**
 * @author huzihao
 * @since 2020/11/5 19:49
 */
public class DellMouseFactory implements MouseFactory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
