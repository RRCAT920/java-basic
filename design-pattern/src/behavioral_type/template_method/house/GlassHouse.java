package behavioral_type.template_method.house;

/**
 * @author huzihao
 * @since 2020/11/9 15:57
 */
public class GlassHouse extends HouseTemplate {
    @Override
    public void buildPillars() {
        System.out.println("建玻璃柱子");
    }

    @Override
    public void buildWalls() {
        System.out.println("建玻璃墙");
    }
}
