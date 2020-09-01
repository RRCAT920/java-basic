package factory.method;

public enum PekingPizzaFactory implements PizzaCreatable {
    INSTANCE;

    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;

        switch (type.toLowerCase()) {
            case "greek" -> pizza = new PekingGreekPizza();
            case "cheese" -> pizza = new PekingCheesePizza();
        }

        return pizza;
    }
}
