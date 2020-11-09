package behavioral_type.template_method.house;

/**
 * @author huzihao
 * @since 2020/11/9 15:58
 */
public class TemplateMethodPatternTest {
    public static void main(String[] args) {
        HouseTemplate houseTemplate = new WoodenHouse();
        houseTemplate.build();

        System.out.println("////////////////////");

        houseTemplate = new GlassHouse();
        houseTemplate.build();
    }
}
