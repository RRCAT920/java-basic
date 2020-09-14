package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import jdk8.data.Employee;

/**
 * @author huzihao
 * @since 2020/9/13 03:51
 */
public class LambdaTest {
    // 接口
    // 默认方法
    // 静态方法

    // 日期时间API
    // Local系列
    // Instant
    // DateTimeFormatter

    // 注解
    // 可重复注解
    // 类型注解

    // 集合
    // 空参构造器延迟计算
    // HashMap底层添加红黑树

    @Test
    public void testMethodRef() {
        var intComp = (Comparator<Integer>) Integer::compare;
        System.out.println(intComp.compare(1, 2));

        var runnable = (Runnable) () -> {
            System.out.println("hello");
        };
        runnable.run();
    }

    /**
     * 无参无返回值
     */
    @Test
    public void nonArgNonReturn() {
        var runnable = (Runnable) () -> {
            System.out.println("无参无返回值");
        };
        runnable.run();
    }

    /**
     * 一参无返回值
     */
    @Test
    public void oneArgNonReturn() {
        var consumer = (Consumer<String>) (String i) -> {
            System.out.println("一个参数" + i + "无返回值");
        };
        consumer.accept("ℹ️");
    }

    /**
     * 参数列表类型推断
     */
    @Test
    public void typeInfer() {
        var consumer = (Consumer<String>) (str) -> {
            System.out.println(str);
        };
        consumer.accept("type infer");
    }

    /**
     * 单参省小括号
     */
    @Test
    public void oneArg() {
        var consumer = (Consumer<String>) str -> {
            System.out.println(str);
        };
        consumer.accept("一参省括号");
    }

    /**
     * 多参多语句有返回值
     */
    @Test
    public void manyArgsManyStatementsAndReturn() {
        var intComp = (Comparator<Integer>) (o1, o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return Integer.compare(o1, o2);
        };
        System.out.println(intComp.compare(1, 2));
    }

    /**
     * 单语句可省大括号
     */
    @Test
    public void oneStatement() {
        var intComp = (Comparator<Integer>) (o1, o2) -> Integer.compare(o1, o2);
        System.out.println(intComp.compare(1, 2));
    }


    @Test
    public void filterList() {
        var strings = new ArrayList<String>();
        strings.add("天地上水");
        strings.add("来听个感觉，什么都别说");
        strings.add("烧不尽的野火");
        strings.add("软中华，硬玉溪，头发越短越牛皮");
        strings.add("抢地盘，夹毛居，再大的场合都不得虚");

        var filteredStrings = filter(strings, str -> str.length() <= 11);
        for (var str : filteredStrings) {
            System.out.println(str);
        }
    }

    /**
     * 断定型接口实现过滤器
     */
    private static <T> List<T> filter(List<T> src, Predicate<T> predicate) {
        var dest = new ArrayList<T>();
        for (var item : src) {
            if (predicate.test(item)) dest.add(item);
        }
        return dest;
    }

    /**
     * 调用 Collections.sort()方法，通过定制排序比较两个 Employee(先按
     * 年龄比，年龄相同按姓名比),使用 Lambda 表达式作为参数传递。
     */
    @Test
    public void ageAndNameSort() {
        var employees = new ArrayList<Employee>();
        employees.add(new Employee(1, "Shu ke", 12, 1000));
        employees.add(new Employee(2, "Bei ta", 12, 10000));

        employees.sort(Comparator.comparingInt(Employee::getAge).thenComparing(Employee::getName));
        for (var employee : employees) {
            System.out.println(employee);
        }
    }

    /**
     * <ol>
     *     <li>声明函数式接口，接口中声明抽象方法:public String getValue(String str);</li>
     *     <li>声明类 LambdaTest，类中编写方法使用接口作为参数，将一个 字符串转换成大写，并作为方法的返回值。</li>
     *     <li>再将一个字符串的第 2 个到第 4 个索引位置进行截取子串。</li>
     * </ol>
     */
    @Test
    public void testStringSupplier() {
        System.out.println(LambdaTest1.strHandler("hello", String::toUpperCase).substring(2, 4));
    }

    @FunctionalInterface
    interface StringMapper {
        String getValue(String str);
    }

    static class LambdaTest1 {
        static String strHandler(String str, StringMapper mapper) {
            return mapper.getValue(str);
        }
    }

    /**
     * <ol>
     *     <li>声明一个带两个泛型的函数式接口，泛型类型为<T,R> : T 为参 数，R 为返回值。</li>
     *     <li>接口中声明对应抽象方法</li>
     *     <li>在声明 longHandler 方法，使用接口作为参数，计算两个 long 型参数的和。</li>
     *     <li>再计算两个 long 型参数的乘积</li>
     * </ol>
     */
    @Test
    public void testLongHandler() {
        assert 5 == longHandler(2, 3, Long::sum);
        assert 20 == longHandler(4, 5, (l1, l2) -> l1 * l2);
    }

    private long longHandler(long l1, long l2, MyFunction<Long, Long> fun) {
        return fun.apply(l1, l2);
    }

    @FunctionalInterface
    interface MyFunction<T, R> {
        R apply(T t1, T t2);
    }
}
