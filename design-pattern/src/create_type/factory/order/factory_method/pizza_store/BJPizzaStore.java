package create_type.factory.order.factory_method.pizza_store;

import java.util.Scanner;

import create_type.factory.pizza.BJCheesePizza;
import create_type.factory.pizza.BJGreekPizza;
import create_type.factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/31 11:35
 */
public class BJPizzaStore extends PizzaStore {
    @Override
    protected Pizza createPizza(String type) {
        return switch (type.toLowerCase()) {
            case "cheese" -> new BJCheesePizza();
            case "greek" -> new BJGreekPizza();
            default -> null;
        };
    }

    public static void main(String[] args) {
        var pizzaStore = new BJPizzaStore();
        var in = new Scanner(System.in);
        while (true) {
            System.out.print("你要吃什么披萨：");
            var type = in.nextLine();
            var pizza = pizzaStore.orderPizza(type);
            if (null == pizza) {
                System.out.println("没有指定披萨");
                break;
            }
            System.out.println(pizza);
        }
    }
}
