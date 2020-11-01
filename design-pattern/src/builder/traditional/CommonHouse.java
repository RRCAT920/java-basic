package builder.traditional;

/**
 * @author huzihao
 * @since 2020/11/1 08:02
 */
public class CommonHouse extends AbstractHouse {
    @Override
    public void layFoundation() {
        System.out.println("普通房打地基");
    }

    @Override
    public void buildWalls() {
        System.out.println("普通房砌墙");
    }

    @Override
    public void cap() {
        System.out.println("普通房封顶");
    }
}
