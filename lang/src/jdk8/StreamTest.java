package jdk8;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import jdk8.data.Employee;
import jdk8.data.EmployeeData;
import jdk8.data.Timer;
import jdk8.data.Trader;
import jdk8.data.Transaction;

/**
 * @author huzihao
 * @since 2020/9/13 19:56
 */
public class StreamTest {
    private static List<Employee> employees;

    @Before
    public void init() {
        employees = EmployeeData.getEmployees();
    }

    @Test
    public void streamIterate() {
        Stream.iterate(0, i -> i + 2).limit(10).forEach(System.out::println);
    }

    @Test
    public void streamGenerate() {
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }

    /**
     * 理解流的特点
     * <ol>
     *     <li>不保存数据</li>
     *     <li>不更改源</li>
     *     <li>延迟执行</li>
     * </ol>
     */
    @Test
    public void order() {
        employees.stream()
                .map(employee -> {
                    System.out.println("map");
                    return employee.getName();
                })
                .forEach(name -> {
                    System.out.println("forEach");
                    System.out.println(name);
                });
    }

    /**
     * 筛选
     */
    @Test
    public void filterSeries() {
        System.out.println("filter⬇️");
        employees.stream().filter(e -> e.getSalary() >= 7000).forEach(System.out::println);

        System.out.println("distinct⬇️");
        employees.add(new Employee(1000, "刘强东", 45, 8000));
        employees.add(new Employee(1000, "刘强东", 45, 8000));
        employees.add(new Employee(1000, "刘强东", 45, 8000));
        employees.add(new Employee(1000, "刘强东", 45, 8000));
        employees.add(new Employee(1000, "刘强东", 45, 8000));
        employees.stream().distinct().forEach(System.out::println);
    }

    /**
     * 切片
     */
    @Test
    public void sliceSeries() {
        System.out.println("limit⬇️");
        employees.stream().limit(4).forEach(System.out::println);
        System.out.println("skip⬇️");
        employees.stream().skip(3).forEach(System.out::println);
    }

    /**
     * 映射
     */
    @Test
    public void mapSeries() { // 这个方法会跑的比较久
        System.out.println("获取长度大于3的员工姓名⬇️");
        employees.stream()
                .filter(employee -> employee.getName().length() > 3)
                .map(Employee::getName)
                .forEach(System.out::println);
//        Integer.MAX_VALUE / 10
        for (int i = 1110; i < 2000; i++) {
            employees.add(new Employee(i, "刘强东", 45, 8000));
        }

        long timeFP = ((Timer<Map<Character, Integer>>) StreamTest::wordCountFP).time();
        long timeOOP = ((Timer<Map<Character, Integer>>) StreamTest::wordCountOOP).time();

        System.out.println("名字的字计数⬇️FP版本");
        System.out.println(timeFP + "ms");
        System.out.println("名字的字计数⬇️OOP版本");
        System.out.println(timeOOP + "ms");

    }

    private static Map<Character, Integer> wordCountFP() {
        var wordCounter = new HashMap<Character, Integer>();
        employees.parallelStream()
                .flatMapToInt(employee -> employee.getName().chars())
                .forEach(i -> {
                    var word = (char) i;
                    wordCounter.put(word, wordCounter.containsKey(word) ?
                            wordCounter.get(word) + 1 : 1);
                });

        return wordCounter;
    }

    private static Map<Character, Integer> wordCountOOP() {
        var wordCounter = new HashMap<Character, Integer>();
        for (var employee : employees) {
            var name = employee.getName();

            for (int i = 0; i < name.length(); i++) {
                var word = name.charAt(i);

                if (null != wordCounter.putIfAbsent(word, 1)) {
                    wordCounter.put(word, wordCounter.get(word) + 1);
                }
            }
        }

        return wordCounter;
    }

    /**
     * 排序
     */
    @Test
    public void sort() {
        System.out.println("自然排序⬇️");
        var numbers = new ArrayList<>(Arrays.asList(1909, 123, 17238, 1283, 123));
        numbers.stream().sorted().forEach(System.out::println);

        System.out.println("定制排序⬇️");
        var employees = EmployeeData.getEmployees();
        try {
            employees.stream().sorted().forEach(System.out::println);
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
        }

        employees.stream()
                .sorted((e1, e2) -> -Integer.compare(e1.getAge(), e2.getAge()))
                .forEach(System.out::println);
    }

    /**
     * 匹配
     */
    @Test
    public void matchSeries() {
        var isAgeLarger18 = employees.stream().allMatch(employee -> employee.getAge() > 18);
        assert !isAgeLarger18;

        var hasAnySalaryLarger1e4 = employees.stream()
                .anyMatch(employee -> employee.getSalary() > 1e4);
        assert !hasAnySalaryLarger1e4;

        var hasNonLei = employees.stream()
                .noneMatch(employee -> employee.getName().startsWith("雷"));
        assert !hasNonLei;
    }

    /**
     * 查找
     */
    @Test
    public void querySeries() {
        System.out.println(employees.stream().findFirst());
        System.out.println(employees.stream().findAny());
        // 不是最优解
        assert 8L == employees.stream().count();
        var maxSalary = employees.stream()
                .map(Employee::getSalary).max(Double::compareTo);
        System.out.println(maxSalary);

        var employee = employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
        System.out.println(employee);
    }

    /**
     * 内部迭代
     */
    @Test
    public void iterate() {
        employees.stream().map(Employee::getAge).forEach(System.out::println);
    }

    /**
     * 归约
     */
    @Test
    public void reduce() {
        var numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        var numberSum = numbers.stream().reduce(0, Integer::sum);
        System.out.println(numberSum);

        var sum = employees.stream()
                .mapToDouble(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(sum);
    }

    /**
     * 给定一个数字列表，如何返回一个由每个数的平方构成的列表呢?
     * 例如，给定【1，2，3，4，5】， 应该返回【1，4，9，16，25】
     */
    @Test
    public void square() {
        Arrays.asList(1, 2, 3, 4, 5).stream()
                .map(integer -> integer * integer)
                .forEach(System.out::println);
    }

    /**
     * 怎样用 map 和 reduce 方法数一数流中有多少个 Employee 呢?
     */
    @Test
    public void mapReduce() {
        var count = employees.stream()
                .map(employee -> 1)
                .reduce(Integer::sum);
        System.out.println(count.orElse(0));
    }


    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    /**
     * <ol>
     *     <li>找出 2011 年发生的所有交易， 并按交易额排序(从低到高)</li>
     *     <li>交易员都在哪些不同的城市工作过</li>
     *     <li>查找所有来自剑桥的交易员，并按姓名排序</li>
     *     <li>返回所有交易员的姓名字符串，按字母顺序排序</li>
     *     <li>有没有交易员是在米兰工作的</li>
     *     <li>打印生活在剑桥的交易员的所有交易额</li>
     *     <li>所有交易中，最高的交易额是多少</li>
     *     <li>找到交易额最小的交易</li>
     * </ol>
     */
    @Test
    public void tran() {
        System.out.println("找出 2011 年发生的所有交易， 并按交易额排序(从低到高)");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("交易员都在哪些不同的城市工作过");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("查找所有来自剑桥的交易员，并按姓名排序");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("返回所有交易员的姓名字符串，按字母顺序排序");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("有没有交易员是在米兰工作的");
        var milan = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));
        System.out.println(milan);
        System.out.println();

        System.out.println("打印生活在剑桥的交易员的所有交易额");
        var sum = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(sum.orElse(0));
        System.out.println();

        System.out.println("所有交易中，最高的交易额是多少");
        var max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println(max.orElse(0));
        System.out.println();

        System.out.println("找到交易额最小的交易");
        var transactionOptional = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));
        System.out.println(transactionOptional.orElse(new Transaction()));
        System.out.println();
    }
}
