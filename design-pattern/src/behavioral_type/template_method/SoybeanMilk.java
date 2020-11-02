package behavioral_type.template_method;

/**
 * @author huzihao
 * @since 2020/11/3 00:51
 */
public abstract class SoybeanMilk {
    /**
     * 建议final
     * template method
     */
    public final void make() {
        select();
        if (wantCondiments()) addCondiments();
        soak();
        stir();
    }

    private void select() {
        System.out.println("1.选择新鲜黄豆");
    }

    protected abstract void addCondiments();

    private void soak() {
        System.out.println("3.浸泡");
    }

    private void stir() {
        System.out.println("4.搅拌");
    }

    /**
     * 钩子方法
     *
     * @return true/false
     */
    protected boolean wantCondiments() {
        return true;
    }
}
