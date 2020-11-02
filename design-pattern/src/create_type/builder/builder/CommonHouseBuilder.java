package create_type.builder.builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:16
 */
public class CommonHouseBuilder extends HouseBuilder {
    @Override
    public void layFoundation() {
        System.out.println("普通地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通墙");
    }

    @Override
    public void cap() {
        System.out.println("普通屋顶");
    }
}
