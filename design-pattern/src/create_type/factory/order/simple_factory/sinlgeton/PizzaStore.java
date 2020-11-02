package create_type.factory.order.simple_factory.sinlgeton;

import java.util.Scanner;

import create_type.factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/30 21:22
 */
public class PizzaStore {
    private final SimplePizzaFactory factory;

    public PizzaStore(SimplePizzaFactory factory) {
        this.factory = factory;
    }

    public Pizza orderPizza(String type) {
        var pizza = factory.createPizza(type);
        if (null != pizza) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
        return pizza;
    }

    public static void main(String[] args) {
        var pizzaStore = new PizzaStore(SimplePizzaFactory.PIZZA_FACTORY);
        var in = new Scanner(System.in);
        while (true) {
            System.out.print("你要什么披萨：");
            var type = in.nextLine();
            var pizza = pizzaStore.orderPizza(type);
            if (null == pizza) {
                System.out.println("没有该类披萨");
                break;
            }
            System.out.println(pizza);
        }
    }
}
