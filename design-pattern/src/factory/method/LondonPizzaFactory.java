package factory.method;

public enum LondonPizzaFactory implements PizzaCreatable {
    INSTANCE;
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        switch (type.toLowerCase()) {
            case "greek" -> pizza = new LondonGreekPizza();
            case "cheese" -> pizza = new LondonCheesePizza();
        }

        return pizza;
    }
}
