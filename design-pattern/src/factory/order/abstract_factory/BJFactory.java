package factory.order.abstract_factory;

import factory.pizza.BJCheesePizza;
import factory.pizza.BJGreekPizza;
import factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/31 13:19
 */
public class BJFactory implements AbstractFactory {
    @Override
    public Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "cheese" -> new BJCheesePizza();
            case "greek" -> new BJGreekPizza();
            default -> null;
        };
    }
}
