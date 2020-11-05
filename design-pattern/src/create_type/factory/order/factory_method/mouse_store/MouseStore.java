package create_type.factory.order.factory_method.mouse_store;

import create_type.factory.order.factory_method.mouse_store.factory.DellMouseFactory;
import create_type.factory.order.factory_method.mouse_store.factory.HpMouseFactory;
import create_type.factory.order.factory_method.mouse_store.factory.MouseFactory;

/**
 * @author huzihao
 * @since 2020/11/5 19:49
 */
public class MouseStore {
    public static void main(String[] args) {
        MouseFactory mouseFactory = new HpMouseFactory();
        var hpMouse = mouseFactory.createMouse();
        hpMouse.click();

        mouseFactory = new DellMouseFactory();
        var dellMouse = mouseFactory.createMouse();
        dellMouse.click();
    }
}
