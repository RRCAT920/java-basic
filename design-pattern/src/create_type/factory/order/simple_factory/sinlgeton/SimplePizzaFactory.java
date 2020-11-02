package create_type.factory.order.simple_factory.sinlgeton;

import create_type.factory.pizza.CheesePizza;
import create_type.factory.pizza.GreekPizza;
import create_type.factory.pizza.PepperPizza;
import create_type.factory.pizza.Pizza;

/**
 * 简单工厂模式（静态工厂方法模式）：定义一个创建对象的类，这些对象都享有共同父类
 *
 * @author huzihao
 * @since 2020/10/30 21:20
 */
public enum SimplePizzaFactory {
    PIZZA_FACTORY;

    // 不符合依赖倒置原则，事物应该依赖抽象而不是细节。
    // 简单工厂在此处（高层模块）就已决定要创建的披萨类型（低层模块）了
    //
    // 不符合开闭原则，对扩展开放，对修改关闭。
    // 每次添加新的披萨类型都要修改简单工厂
    public Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "greek" -> new GreekPizza();
            case "cheese" -> new CheesePizza();
            case "pepper" -> new PepperPizza();
            default -> null;
        };
    }
}
