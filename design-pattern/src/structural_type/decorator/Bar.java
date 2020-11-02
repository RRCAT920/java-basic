package structural_type.decorator;

/**
 * @author huzihao
 * @since 2020/11/2 18:36
 */
public class Bar {
    public static void main(String[] args) {
        System.out.println("无因咖啡: ");
        Drink drink = new Decaf();
        System.out.println(drink.getDescription());
        System.out.println(drink.cost());

        System.out.println();
        System.out.println("加牛奶: ");
        drink = new Milk(drink);
        System.out.println(drink.getDescription());

        System.out.println();
        System.out.println("加牛奶: ");
        drink = new Milk(drink);
        System.out.println(drink.getDescription());
        System.out.println(drink.cost());

        System.out.println();
        System.out.println("加巧克力: ");
        drink = new Chocolate(drink);
        System.out.println(drink.getDescription());
        System.out.println(drink.cost());
    }
}
