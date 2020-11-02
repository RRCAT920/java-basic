package behavioral_type.template_method;

/**
 * @author huzihao
 * @since 2020/11/3 00:58
 */
public class PeanutSoybeanMilk extends SoybeanMilk {
    @Override
    protected void addCondiments() {
        System.out.println("2.加入上好花生");
    }
}
