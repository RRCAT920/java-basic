package factory.order.abstract_factory;

import java.util.Scanner;

import factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/31 13:20
 */
public class PizzaStore {
    private static final Scanner SCANNER = new Scanner(System.in);

    private final AbstractFactory factory;

    public PizzaStore(AbstractFactory factory) {
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
        while (true) {
            System.out.print("你要吃哪的披萨：");
            var pizzaStore = choosePizzaStore();
            if (null == pizzaStore) {
                System.out.println("没有该地域的披萨");
                break;
            }
            System.out.print("你要吃什么披萨：");
            var pizza = choosePizza(pizzaStore);
            if (null == pizza) {
                System.out.println("没有指定披萨");
                break;
            }
            System.out.println(pizza);
        }
    }

    private static Pizza choosePizza(PizzaStore pizzaStore) {
        var type = SCANNER.nextLine();
        return pizzaStore.orderPizza(type);
    }

    private static PizzaStore choosePizzaStore() {
        var location = SCANNER.nextLine();
        return switch (location) {
            case "bj" -> new PizzaStore(new BJFactory());
            case "ld" -> new PizzaStore(new LDFactory());
            default -> null;
        };
    }
}
