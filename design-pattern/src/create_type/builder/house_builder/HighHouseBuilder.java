package create_type.builder.house_builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:16
 */
public class HighHouseBuilder extends HouseBuilder {
    @Override
    public void layFoundation() {
        house.setFoundation("高级地基");
    }

    @Override
    public void buildWalls() {
        house.setWalls("高级墙");
    }

    @Override
    public void cap() {
        house.setRoof("高级屋顶");
    }
}
