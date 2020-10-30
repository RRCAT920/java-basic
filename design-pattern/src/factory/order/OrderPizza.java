package factory.order;

import java.util.Scanner;

import factory.pizza.CheesePizza;
import factory.pizza.GreekPizza;
import factory.pizza.Pizza;

/**
 * @author huzihao
 * @since 2020/10/30 18:05
 */
public class OrderPizza {
    private static final Scanner scanner = new Scanner(System.in);

    public OrderPizza() {
        label:
        while (true) {
            Pizza pizza;
            var type = getType();
            switch (type.toLowerCase()) {
                case "greek" -> {
                    pizza = new GreekPizza();
                    pizza.setName("希腊披萨");
                }
                case "cheese" -> {
                    pizza = new CheesePizza();
                    pizza.setName("芝士披萨");
                }
                default -> {
                    break label;
                }
            }

            pizza.prepare();
            pizza.bake();
            pizza.cut();
            pizza.cut();
        }
    }

    private String getType() {
        System.out.print("请输入披萨类型：");
        return scanner.nextLine();
    }
}
