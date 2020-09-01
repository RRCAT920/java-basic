package practice.commonclass;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author huzihao
 * @since 2020/8/30 15:59
 */
public class StringTest {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        new StringTest().toTitle();
    }

    /**
     * 接受给出的一个字母串，先将该串原样输出，然后判断该串的第一个字母是否为大写，
     * 若是大写则统计该串中大写字母的个数，并将所有大写字母输出；否则输出信息串”第一个字母不是大写字母!”。
     */
    public void isTitle() {
        String userInput = in.next();

        System.out.println(userInput);
        var chars = userInput.toCharArray();

        if (!Character.isUpperCase(chars[0])) System.out.println("第一个字母不是大写字母!");
        else {
            for (char aChar : chars) {
                if (Character.isUpperCase(aChar)) System.out.print(aChar);
            }
        }
    }

    /**
     * 接受用户输入的一行字符串，统计字符个数，然后反序输出。
     */
    public void printReversely() {
        String userInput = in.next();

        System.out.println("字符个数：" + userInput.length());

        var strBuffer = new StringBuilder(userInput);
        System.out.println("反转：" + strBuffer.reverse());
    }


    /**
     * 从键盘读取用户输入两个字符串，并重载3个函数分别实现这两个字符串的拼接、整数相加和浮点数相加。要进行异常处理，对输入的不
     * 符合要求的字符串提示给用户，不能使程序崩溃。
     */
    public void plusTwoInput() {
        String res1 = in.next();
        String res2 = in.next();

        int i1;
        int i2;
        double d1;
        double d2;

        try {
            i1 = Integer.parseInt(res1);
            i2 = Integer.parseInt(res2);
            d1 = Double.parseDouble(res1);
            d2 = Double.parseDouble(res2);
        } catch (NumberFormatException e) {
            System.out.println("请仅输入数字");
            return;
        }

        System.out.println(add(res1, res2));
        System.out.println(add(i1, i2));
        System.out.println(add(d1, d2));
    }

    private String add(String s1, String s2) {
        return s1 + s2;
    }

    private int add(int i1, int i2) {
        return i1 + i2;
    }

    private double add(double d1, double d2) {
        return d1 + d2;
    }

    /**
     * 模拟一个trim方法，去除字符串两端的空格。
     */
    public void testTrim() {
        while (true) {
            var str = in.nextLine();

            System.out.println("结果：--" + myTrim(str) + "--");
        }
    }

    private String myTrim(String str) {
        var chars = str.toCharArray();
        int i = 0;
        int j = str.length() - 1;
        while (i < j) {
            if (chars[i] > ' ' && chars[j] > ' ') break;
            if (chars[i] <= ' ') i++;
            if (chars[j] <= ' ') j--;
        }

        //空白字符串
        if (chars[i] == ' ') return "";
        return str.substring(i, j + 1);
    }

    /**
     * 将字符串中指定部分进行反转。比如将“abcdefg”反转为”abfedcg”
     */
    public void reverseStr() {
        System.out.println("格式：字符串 开始索引 结束索引");

        var chars = in.next().toCharArray();
        var startIndex = in.nextInt();
        var endIndex = in.nextInt();

        for (int offset = 0; offset < (endIndex - startIndex) / 2; offset++) {
            var aChar = chars[startIndex + offset];
            chars[startIndex + offset] = chars[endIndex - 1 - offset];
            chars[endIndex - 1 - offset] = aChar;
        }

        System.out.println(new String(chars));
    }


    @Test
    public void testCount() {
        assert 0 == count("123", "13");
        assert 0 == count("123", "456");
        assert 1 == count("12", "123");
        assert 2 == count("12", "12123");
    }

    /**
     * 获取一个字符串在另一个字符串中出现的次数。判断target在source中出现的次数
     */
//    private int count(String target, String source) {
//        int length1 = target.length();
//        int length2 = source.length();
//        var count = 0;
//
//        for (int i = 0; i + length1 <= length2; i++) {
//            if (target.equals(source.substring(i, i + length1))) count++;
//        }
//
//        return count;
//    }
    private int count(String target, String source) {
        int count = 0;
        int i = 0;

        while ((i = source.indexOf(target)) != -1) {
            count++;
            source = source.substring(i + target.length());
        }

        return count;
    }


    @Test
    public void testCommonString() {
        assert "123".equals(commonString("123", "1234").get(0));
        assert "12".equals(commonString("1256", "123456").get(0));
        assert "56".equals(commonString("1256", "123456").get(1));
    }
    /**
     * 获取两个字符串中最大相同子串。
     */
    public List<String> commonString(String str1, String str2) {
        var strings = new ArrayList<String>(3);
        var minStr = str1.length() < str2.length() ? str1 : str2;
        var maxStr = str1 == minStr ? str2 : str1;

        for (int length = minStr.length(); length > 0; length--) {
            for (int i = 0; i + length <= minStr.length(); i++) {
                var str = minStr.substring(i, i + length);
                if (maxStr.contains(str)) {
                    strings.add(str);
                }
            }

            if (0 != strings.size()) {
                break;
            }
        }

        return strings;
    }

    @Test
    public void testSort() {
        String str = "adc";
        str = sort(str);
        assert "acd".equals(str);
        assert "ac".equals(str);
    }

    /**
     * 对字符串中字符进行自然顺序排序
     */
    public String sort(String str) {
        var chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    /**
     * 中国有句俗语叫“三天打鱼两天晒网”。如果从1990年1月1日起开始执行“三天打鱼两天晒网”。
     * 如何判断在以后的某一天中是“打鱼”还是“晒网”？
     */
    @Test
    public void ThreeDayFishing() {
        var start = LocalDate.of(1990, 1, 1);
        var now = LocalDate.of(2020, 8, 31);
//        0,4 -> 晒网
        int count = 1;
        for (; start.isBefore(now); count++) {
            start = start.plusDays(1);
        }

        switch (count / 5) {
            case 0, 4 -> System.out.println("晒网🕸️");
            default -> System.out.println("打渔🐠");
        }
    }

    /**
     * 任意给定的一串字母，统计字符串里面的大写字母和小写字母的个数
     */
    @Test
    public void countUpperAndLower() {
        var upperCount = 0;
        var lowerCount = 0;

        var str = "jadkJDKLDjksaldfJKL";

        for (int i = 0; i < str.length(); i++) {
            var aChar = str.charAt(i);
            if (Character.isUpperCase(aChar)) {
                upperCount++;
            } else if (Character.isLowerCase(aChar)) {
                lowerCount++;
            }
        }

        assert 8 == upperCount;
        assert str.length() - upperCount == lowerCount;
    }

    /**
     * 根据传入得路径，获取文件名。例如：D:\myfile\hello.java取出hello.java
     */
    @Test
    public void extractFileName() {
        var path = "D:\\myfile\\hello.java";
        var index = path.lastIndexOf('\\') + 1;

        var fileName = path.substring(index);

        assert fileName.equals("hello.java");
    }

    /**
     * 根据传入得路径，获取文件的类型名。例如：D:\myfile\hello.java取出.java
     */
    @Test
    public void extractTypeExt() {
        var path = "D:\\myfile\\hello.java";
        int index = path.lastIndexOf('.');

        var type = path.substring(index);

        assert ".java".equals(type);
    }

    /**
     * 求两个日期之间相隔的天数
     */
    @Test
    public void testDaysBetween() {
        int days = daysBetween("2010-09-20", "2010-09-21");

        assert 1 == days;
    }

    private int daysBetween(String start, String end) {
        var formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        var startDate = LocalDate.from(formatter.parse(start));
        var endDate = LocalDate.from(formatter.parse(end));
        int count = 0;

        for (; startDate.isBefore(endDate); count++) {
            startDate = startDate.plusDays(1);
        }

        return count;
    }

    /**
     * 随便输入两个单词，两个单词之间以空格隔开，输出时每个单词的首字母变为大写。如输入：“hello java”，输出为“Hello Java”
     */
    public void toTitle() {
        var str1 = in.next();
        var str2 = in.next();

        str1 = str1.replace(str1.charAt(0), Character.toTitleCase(str1.charAt(0)));
        str2 = str2.replace(str2.charAt(0), Character.toTitleCase(str2.charAt(0)));

        System.out.println(str1 + " " + str2);
    }

    /**
     * 求出“hijavahehejavahejava”字符串里面包含几个“java字符串。
     */
    @Test
    public void countJava() {
        var str = "hijavahehejavahejava";
        var target = "java";
        int count = 0;
        int index = 0;

        while ((index = str.indexOf(target)) != -1) {
            count++;
            str = str.substring(index + target.length());
        }

        assert 3 == count;
    }

    /**
     * 输出字符串"ddejidsEFALDFfnef2357 3ed"里的大写字母数，小写英文字母数，非英文字母数
     */
    @Test
    public void filter() {
        var chars = "ddejidsEFALDFfnef2357 3ed".toCharArray();
        int countUpper = 0;
        int countLower = 0;
        int countNonLetter = 0;

        for (char aChar : chars) {
            if (Character.isUpperCase(aChar)) countUpper++;
            else if (Character.isLowerCase(aChar)) countLower++;
            else if (!Character.isLetter(aChar)) countNonLetter++;
        }

        System.out.println("大写字母数：" + countUpper);
        System.out.println("小写字母数：" + countLower);
        System.out.println("非英文字母数：" + countNonLetter);
    }

    /**
     * 输入一句5个字的话，然后将它逆序输出。例如：
     * 原数组：我爱你中国
     * 逆序输出：国中你爱我
     * 提示：先声明一个字符串数组，每个汉字作为字符串数组的一个元素，然后再从数组末尾开始循环输出。
     */
    @Test
    public void reversePrint() {
        var str = "我爱你中国";
        var strBuilder = new StringBuilder(str);
        strBuilder.reverse();
        System.out.println(strBuilder.toString());
    }
}
