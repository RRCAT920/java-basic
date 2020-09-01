package factory.method;

import java.util.Scanner;

public class Main {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print("请输入您想要的披萨🍕地域(q to esc)：");
            String input = in.next().toLowerCase();

            System.out.print("请输入您想要的披萨🍕类型(q to esc)：");
            String type = in.next().toLowerCase();

            if ("q".equals(input) || "q".equals(type)) break;

            Pizza pizza = null;
            switch (input) {
                case "london" -> pizza = LondonPizzaFactory.INSTANCE.createPizza(type);
                case "peking" -> pizza = PekingPizzaFactory.INSTANCE.createPizza(type);
            }
            if (pizza != null) {
                pizza.prepare();
                pizza.bake();
                pizza.cut();
                pizza.box();
            }
        }
    }
}
