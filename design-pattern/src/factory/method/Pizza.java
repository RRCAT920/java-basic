package factory.method;

public class Pizza {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void prepare() {
        System.out.println(title(name) + " is preparing");
    }

    public void bake() {
        System.out.println(title(name) + " is baking");
    }

    public void cut() {
        System.out.println(title(name) + " is cutting");
    }

    public void box() {
        System.out.println(title(name) + " is boxing");
    }

    private String title(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
