package practice.enumclass;

import org.junit.Test;

/**
 * @author huzihao
 * @since 2020/9/1 21:56
 */
public class EnumTest {
    @Test
    public void testMonth() {
        assert 31 == Month.JANUARY.getDays();
        assert 28 == Month.FEBRUARY.getDays();
        assert 31 == Month.MARCH.getDays();
        assert 30 == Month.APRIL.getDays();
        assert 31 == Month.MAY.getDays();
        assert 30 == Month.JUNE.getDays();
    }

    @Test
    public void testWeek() {
        assert "Work hard❤️".equals(Week.MONDAY.getPlan());

        for (int i = 1; i < 8; i++) {
            Week week = null;
            switch (i) {
                case 1 -> week = Week.MONDAY;
                case 2 -> week = Week.TUESDAY;
                case 3 -> week = Week.WEDNESDAY;
                case 4 -> week = Week.THURSDAY;
                case 5 -> week = Week.FRIDAY;
                case 6 -> week = Week.SATURDAY;
                case 7 -> week = Week.SUNDAY;
            }
            System.out.println(week + " <-> " + printWeek(week));
        }
    }

    private String printWeek(Week week) {
        return switch (week) {
            case MONDAY -> "星期1⃣️";
            case TUESDAY -> "星期2⃣️";
            case WEDNESDAY -> "星期3⃣️";
            case THURSDAY -> "星期4⃣️";
            case FRIDAY -> "星期5⃣️";
            case SATURDAY -> "星期6⃣️";
            case SUNDAY -> "星期7⃣️";
        };
    }

    @Test
    public void testColor() {
        var red = Color.RED;
        assert "rgb(255,0,0)".equals(red.toString());
        assert "红色".equals(red.meaning());

        assert "红色".equals(red.reflectColor());
        assert "绿色".equals(Color.GREEN.reflectColor());
    }
}

/**
 * 创建月份枚举类，枚举值包含十二个月份，月份要求用英文单词
 */
enum Month {
    JANUARY(31),
    FEBRUARY(28),
    MARCH(31),
    APRIL(30),
    MAY(31),
    JUNE(30),
    JULY(31),
    AUGUST(31),
    September(30),
    October(31),
    November(30),
    December(31);

    Month(int days) {
        this.days = days;
    }

    private final int days;

    public int getDays() {
        return days;
    }
}

/**
 * 创建星期枚举类，有7个枚举值，包含计划属性plan，为此属性赋值（使用构造器）
 */
enum Week {
    MONDAY("Work hard❤️"),
    TUESDAY("Have a rest😄"),
    WEDNESDAY("Study hard📚"),
    THURSDAY("Go to gym🏟️"),
    FRIDAY("Communication👂"),
    SATURDAY("Sid Meier's Civilization VI6⃣️"),
    SUNDAY("Scala Fun!🉑️");

    private final String plan;

    Week(String plan) {
        this.plan = plan;
    }

    public String getPlan() {
        return plan;
    }
}

/**
 * 创建一个Color枚举类，
 * 1)有 RED,BLUE,BLACK,YELLOW,GREEN这个五个枚举值；
 * 2)Color有三个属性redValue，greenValue，blueValue，
 * 3)创建构造方法，参数包括这三个属性，
 * 4)每个枚举值都要给这三个属性赋值，三个属性对应的值分别是red：255,0,0  	blue:0,0,255  black:0,0,0  yellow:255,255,0  green:0,255,0
 * 5)重写toString方法显示三属性的值
 * 6)在Color中添加抽象方法meaning，不同的枚举类的meaning代表的意思各不相同
 */
enum Color implements Works {
    RED(255, 0, 0) {
        @Override
        public String meaning() {
            return "红色";
        }
    },
    BLUE(0, 0, 255) {
        @Override
        public String meaning() {
            return "蓝色";
        }
    },
    BLACK(0, 0, 0) {
        @Override
        public String meaning() {
            return "黑色";
        }
    },
    YELLOW(255, 255, 0) {
        @Override
        public String meaning() {
            return "黄色";
        }
    },
    GREEN(0, 255, 0) {
        @Override
        public String meaning() {
            return "绿色";
        }
    };

    private final int redValue;
    private final int greenValue;
    private final int blueValue;

    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public String toString() {
        return "rgb(" + redValue + "," + greenValue + "," + blueValue + ")";
    }

    public abstract String meaning();


    @Override
    public String reflectColor() {
        return this.meaning();
    }
}

/**
 * 有一个接口类Works，有个反射颜色的接口ReflectColor，用上题的Color枚举类实	现这个接口类，每个枚举值反射自己颜色的光。
 */
interface Works {
    String reflectColor();
}