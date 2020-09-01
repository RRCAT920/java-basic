package enumclass;

/**
 * @author huzihao
 * @since 2020/8/27 19:40
 */
public class Season {
    private final String name;
    private final String description;

    /**
     * Don't let anyone instantiate this class
     * @param name the name of season
     * @param description the description of season
     */
    private Season(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Season{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static final Season SPRING = new Season("春天", "春暖花开");
    public static final Season SUMMER = new Season("夏天", "炎炎夏日");
    public static final Season AUTUMN = new Season("秋天", "秋风萧瑟");
    public static final Season WINTER = new Season("冬天", "寒冬腊月");
}
