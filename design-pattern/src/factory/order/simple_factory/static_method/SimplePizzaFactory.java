package factory.order.simple_factory.static_method;

import factory.pizza.CheesePizza;
import factory.pizza.GreekPizza;
import factory.pizza.PepperPizza;
import factory.pizza.Pizza;

/**
 * 简单工厂模式（静态工厂方法模式）：定义一个创建对象的类，这些对象都享有共同父类
 *
 * @author huzihao
 * @since 2020/10/30 21:20
 */
public class SimplePizzaFactory {
    /**
     * Don't let anyone to instantiate this class
     */
    private SimplePizzaFactory() {
    }

    public static Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "greek" -> new GreekPizza();
            case "cheese" -> new CheesePizza();
            case "pepper" -> new PepperPizza();
            default -> null;
        };
    }
}
