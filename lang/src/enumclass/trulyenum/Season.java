package enumclass.trulyenum;

/**
 * @author huzihao
 * @since 2020/8/27 19:40
 */
public enum Season implements Displayable {
    SPRING("春天", "春暖花开") {
        @Override
        public void show() {
            System.out.println("春天🌳在哪里呀？");
        }
    },
    SUMMER("夏天", "炎炎夏日") {
        @Override
        public void show() {
            System.out.println("夏天🔥在哪里呀？");
        }
    },
    AUTUMN("秋天", "秋风萧瑟") {
        @Override
        public void show() {
            System.out.println("秋天🍁在哪里呀？");
        }
    },
    WINTER("冬天", "寒冬腊月") {
        @Override
        public void show() {
            System.out.println("冬天❄️在哪里呀？");
        }
    };

    private final String name;
    private final String description;

    /**
     * Don't let anyone instantiate this class
     * @param name the name of season
     * @param description the description of season
     */
    Season(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static void main(String[] args) {
        for (Season season : Season.values()) {
            season.show();
        }
    }
}

interface Displayable {
    void show();
}