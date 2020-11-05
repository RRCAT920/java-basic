package create_type.builder.house_builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:16
 */
public class CommonHouseBuilder extends HouseBuilder {
    @Override
    public void layFoundation() {
        house.setFoundation("普通地基");
    }

    @Override
    public void buildWalls() {
        house.setWalls("普通墙");
    }

    @Override
    public void cap() {
        house.setRoof("普通屋顶");
    }
}
