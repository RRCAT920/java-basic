package behavioral_type.template_method;

/**
 * @author huzihao
 * @since 2020/11/3 00:55
 */
public class RedBeanSoybeanMilk extends SoybeanMilk {
    @Override
    public void addCondiments() {
        System.out.println("2.加入上好红豆");
    }
}
