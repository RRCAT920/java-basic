package builder.builder;

/**
 * @author huzihao
 * @since 2020/11/1 09:13
 */
public class House {
    private String foundation;
    private String walls;
    private String roof;

    public House() {
    }

    @Override
    public String toString() {
        return "House{" +
                "foundation='" + foundation + '\'' +
                ", walls='" + walls + '\'' +
                ", roof='" + roof + '\'' +
                '}';
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public String getWalls() {
        return walls;
    }

    public void setWalls(String walls) {
        this.walls = walls;
    }

    public String getRoof() {
        return roof;
    }

    public void setRoof(String roof) {
        this.roof = roof;
    }

    public House(String foundation, String walls, String roof) {
        this.foundation = foundation;
        this.walls = walls;
        this.roof = roof;
    }
}
