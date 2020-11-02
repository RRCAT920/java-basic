package behavioral_type.template_method;

/**
 * @author huzihao
 * @since 2020/11/3 00:58
 */
public class Client {
    public static void main(String[] args) {
        SoybeanMilk soybeanMilk = new RedBeanSoybeanMilk();
        soybeanMilk.make();
        System.out.println("--------------------");
        soybeanMilk = new PeanutSoybeanMilk();
        soybeanMilk.make();
        System.out.println("--------------------");
        soybeanMilk = new PureSoybeanMilk();
        soybeanMilk.make();
    }
}
