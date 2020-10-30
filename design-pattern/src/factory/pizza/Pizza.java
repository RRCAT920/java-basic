package factory.pizza;

/**
 * @author huzihao
 * @since 2020/10/30 17:36
 */
public abstract class Pizza {
    protected String name;

    public void prepare() {
        System.out.println(name + " preparing");
    }

    public void bake() {
        System.out.println(name + " baking");
    }

    public void cut() {
        System.out.println(name + " cutting");
    }

    public void box() {
        System.out.println(name + " boxing");
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "你的" + name;
    }
}
