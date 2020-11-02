package create_type.factory.order.abstract_factory;

import create_type.factory.pizza.LDGreekPizza;
import create_type.factory.pizza.LDPepperPizza;
import create_type.factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/31 13:19
 */
public class LDFactory implements AbstractFactory {
    @Override
    public Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "greek" -> new LDGreekPizza();
            case "pepper" -> new LDPepperPizza();
            default -> null;
        };
    }
}
