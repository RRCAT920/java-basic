package create_type.builder.builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:17
 */
public class HouseDirector {
    private final HouseBuilder houseBuilder;

    public House construct() {
        houseBuilder.layFoundation();
        houseBuilder.buildWalls();
        houseBuilder.cap();
        return houseBuilder.build();
    }

    public HouseDirector(HouseBuilder houseBuilder) {
        this.houseBuilder = houseBuilder;
    }
}
