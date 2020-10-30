package factory.tranditional_order;

import java.util.Scanner;

import factory.pizza.CheesePizza;
import factory.pizza.GreekPizza;
import factory.pizza.PepperPizza;
import factory.pizza.Pizza;

/**
 * 相当于客户端
 *
 * @author huzihao
 * @since 2020/10/30 20:02
 */
public class PizzaStore {
    private static final Scanner scanner = new Scanner(System.in);

    public Pizza orderPizza(String type) {
        Pizza pizza = null;
        switch (type.toLowerCase()) {
            case "greek" -> pizza = new GreekPizza();
            case "cheese" -> pizza = new CheesePizza();
            case "pepper" -> pizza = new PepperPizza();
        }

        if (null != pizza) {
            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.box();
        }
        return pizza;
    }

    public static void main(String[] args) {
        var pizzaStore = new PizzaStore();
        while (true) {
            System.out.print("你要的披萨类型：");
            var type = scanner.nextLine();
            var pizza = pizzaStore.orderPizza(type);
            if (null == pizza) break;
            System.out.println(pizza);
        }
    }
}
