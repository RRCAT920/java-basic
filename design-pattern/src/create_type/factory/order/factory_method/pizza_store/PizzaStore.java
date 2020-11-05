package create_type.factory.order.factory_method.pizza_store;

import create_type.factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/31 11:33
 */
public abstract class PizzaStore {
    public Pizza orderPizza(String type) {
        var pizza = createPizza(type);
        if (null != pizza) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
        return pizza;
    }

    protected abstract Pizza createPizza(String type);
}
