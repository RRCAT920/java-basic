package builder.builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:16
 */
public class HighHouseBuilder extends HouseBuilder {
    @Override
    public void layFoundation() {
        System.out.println("高级地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("高级墙");
    }

    @Override
    public void cap() {
        System.out.println("高级屋顶");
    }
}
