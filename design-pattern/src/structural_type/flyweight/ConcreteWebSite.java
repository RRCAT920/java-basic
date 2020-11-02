package structural_type.flyweight;

/**
 * @author huzihao
 * @since 2020/11/2 22:23
 */
public class ConcreteWebSite extends WebSite {
    /**
     * 共享部分，内部状态
     */
    private final String type;

    public ConcreteWebSite(String type) {
        this.type = type;
    }

    @Override
    public void use(User user) {
        System.out.println("网站的发布形式: " + type + "[" + user.getName() + "]");
    }
}
