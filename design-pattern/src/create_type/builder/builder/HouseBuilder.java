package create_type.builder.builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:14
 */
public abstract class HouseBuilder {
    protected House house = new House();

    public abstract void layFoundation();

    public abstract void buildWalls();

    public abstract void cap();

    public House build() {
        return house;
    }
}
