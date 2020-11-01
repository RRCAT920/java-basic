package builder;

/**
 * @author huzihao
 * @since 2020/11/1 07:59
 */
public abstract class AbstractHouse {
    public abstract void layFoundation();

    public abstract void buildWalls();

    public abstract void cap();

    public void build() {
        layFoundation();
        buildWalls();
        cap();
    }
}
