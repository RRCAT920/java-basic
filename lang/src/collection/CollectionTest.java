package collection;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author huzihao
 * @since 2020/9/2 01:48
 */
public class CollectionTest {
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void main(String[] args) {
        Collection collection = new ArrayList();

        collection.add("add");
        collection.add(123);
        collection.add(123.3);
        collection.add(collection);

        System.out.println(collection.size());

        // 添加另一个集合中的元素
        Collection collection1 = new ArrayList();
        collection1.add("another1");
        collection1.add("another2");

        collection1.addAll(collection);

        assert 6 == collection1.size();
        System.out.println(collection1);

        collection1.clear();
        assert 0 == collection1.size();

        Collection col = Arrays.asList(1213, 221312);
        System.out.println(col);
    }

    /**
     * clear
     * remove
     * removeAll
     * retainAll
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void deleteSeries() {
        Collection collection = new ArrayList(5);
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.clear();
        assert collection.isEmpty();

        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.remove(1);
        Collection collection1 = new ArrayList(5);
        collection1.addAll(collection);
        collection1.remove(1);
        assert collection1.equals(collection);

        collection.clear();
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.removeAll(collection1);
        collection1.clear();
        collection1.add(1);
        assert collection1.equals(collection);

        Collection intersection = new ArrayList(2);
        intersection.add(1);
        intersection.add(2);
        Collection A = new ArrayList(5);
        Collection B = new ArrayList(5);
        A.add(1);
        A.add(2);
        A.add(3);
        A.add(4);

        B.add(1);
        B.add(2);
        B.add(5);
        B.add(6);

        A.retainAll(B);
        assert intersection.equals(A);
    }

    /**
     * contains
     * containsAll
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void containsSeries() {
        Collection collection = new ArrayList(5);
        collection.add(1);
        collection.add(2);
        collection.add(3);

        assert collection.contains(1);

        Collection subCollection = new ArrayList(5);
        subCollection.add(2);
        subCollection.add(3);

        assert collection.containsAll(subCollection);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Test
    public void testIterator() {
        Collection collection = new ArrayList(5);
        collection.add(1);
        collection.add(2);
        collection.add(3);
        collection.add(null);

        Iterator itor = collection.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }
    }

    @Test
    public void testForEach() {
        Collection collection = Arrays.asList(1, 2, 3);
        collection.forEach(System.out::println);
    }

    /**
     * 定义一个Collection接口类型的变量，引用一个Set集合的实现类，实现添加单个元素，添加另一个集合，删除元素，
     * 判断集合中是否包含一个元素，判断是否为空，清除集合，返回集合里元素的个数等常用操作。
     */
    @Test
    public void testCollection() {
        Collection<Integer> set = new HashSet<>();

        set.add(1);
        assert set.contains(1);
        assert !set.isEmpty();
        assert 1 == set.size();

        set.clear();
        assert set.isEmpty();

        set.addAll(Arrays.asList(1, 2, 3, 4));
        assert "[1, 2, 3, 4]".equals(set.toString());

        set.remove(1);
        assert "[2, 3, 4]".equals(set.toString());
    }

    /**
     * 创建Set接口的实现类，添加10个以上的元素，通过Iterator遍历此集合元素。
     * 创建Set接口的实现类，添加10个以上的元素，通过foreach遍历此集合元素。
     * 创建Set接口的实现类，添加10个以上的元素，要求能够排序。
     */
    @Test
    public void traverseSet() {
        var set = new HashSet<Integer>();

        set.add(1);
        set.add(4);
        set.add(1);
        set.add(2);
        set.add(5);
        set.add(3);
        set.add(6);
        set.add(8);
        set.add(9);
        set.add(7);
        set.add(8);
        set.add(10);

        var itor = set.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }

        System.out.println("-------");

        for (Integer integer : set) {
            System.out.println(integer);
        }

        var treeSet = new TreeSet<Integer>(((o1, o2) -> -Integer.compare(o1, o2)));
        treeSet.addAll(set);
        System.out.println("-------");
        treeSet.forEach(System.out::println);
    }

    /**
     * 创建Car类，包含name，price属性，构造器等方法，创建测试类，在main方法中创建Set接口的实现类，
     * 添加5个以上的Car对象，遍历集合元素，验证重复元素是否过滤了；如果没有过滤，实现过滤功能；
     * 把每个小车的price降10000元，再遍历，查看price是否已改变
     */
    @Test
    public void testCar() {
        class Car {
            String name;
            double price;

            public Car(String name, double price) {
                this.name = name;
                this.price = price;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Car car = (Car) o;
                return Double.compare(car.price, price) == 0 &&
                        Objects.equals(name, car.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, price);
            }

            @Override
            public String toString() {
                return "Car{" +
                        "name='" + name + '\'' +
                        ", price=" + price +
                        '}';
            }
        }

        Car[] cars = {
                new Car("Audi", 20e4),
                new Car("BYD", 2e4),
                new Car("Benz", 50e4),
                new Car("Audi A6", 30e4),
                new Car("Audi A8", 40e4),
                new Car("Audi A8", 40e4)
        };
        var hashSet = new HashSet<Car>(Arrays.asList(cars));

        hashSet.forEach(System.out::println);

        System.out.println("---------------");
        System.out.println("在同一地址操作");
        System.out.println("---------------");

        for (var car : cars) {
            car.price -= 1e4;
        }
        hashSet.forEach(System.out::println);
    }

    /**
     * 创建ArrayList实例化对象，添加10个以上的元素，在2号位插入一个元素，获得5号位元素，删除6号位元素，修改7号位的元素；
     * 通过四种方法遍历此题中的集合
     */
    @Test
    public void CRUDInArrayList() {
        var arrayList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));

        arrayList.add(1, 100);

        assert 4 == arrayList.get(4);

        var integer = arrayList.remove(5);
        assert 5 == integer;

        integer = arrayList.set(6, 99);
        assert 7 == integer;

        System.out.println("1.---------");
        System.out.println(arrayList);

        System.out.println("2.---------");
        arrayList.forEach(System.out::println);

        System.out.println("3.---------");
        var itor = arrayList.iterator();
        while (itor.hasNext()) {
            System.out.println(itor.next());
        }

        System.out.println("4.---------");
        for (Integer integer1 : arrayList) {
            System.out.println(integer1);
        }

        System.out.println("5.---------");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println(arrayList.get(i));
        }
    }

    /**
     * 创建LinkedList实例化对象，练习具有队列特点的方法
     */
    @Test
    public void queue() {
        var queue = new LinkedList<Integer>();

        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.addLast(5);

        while (!queue.isEmpty()) {
            var first = queue.removeFirst();
            System.out.println(first);
        }
    }

    /**
     * 按要求实现下列问题：<p>
     * 1)封装一个新闻类，包含标题和内容属性，提供get、set方法，重写toString方法，打印对象时只打印标题；
     * <p>
     * 2)只提供一个带参数的构造器，实例化对象时，只初始化标题；并且实例化两个对象：
     * 新闻一：中国多地遭雾霾笼罩空气质量再成热议话题
     * 新闻二：春节临近北京“卖房热”
     * <p>
     * 3)将新闻对象添加到ArrayList集合中，并且使用ListIterator倒序遍历；
     * <p>
     * 4)在遍历集合过程中，对新闻标题进行处理，超过15字的只保留前14个，然后在后边加“…”
     * <p>
     * 5)在控制台打印遍历出经过处理的新闻标题；
     */
    @Test
    public void news() {
        class News {
            private String title;
            private String content;

            public News(String title) {
                this.title = title;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                News news = (News) o;
                return Objects.equals(title, news.title) &&
                        Objects.equals(content, news.content);
            }

            @Override
            public int hashCode() {
                return Objects.hash(title, content);
            }

            @Override
            public String toString() {
                return title;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

        var list = new ArrayList<>(Arrays.asList(
                new News("中国多地遭雾霾笼罩空气质量再成热议话题"),
                new News("春节临近北京“卖房热”")));

        var itor = list.listIterator(list.size());
        while (itor.hasPrevious()) {
            var news = itor.previous().toString();
            news = news.length() <= 14 ? news : news.substring(0, 14) + "...";
            System.out.println(news);
        }
    }

    /**
     * 定义一个Map接口类型的变量，引用一个实现类，添加键值对，判断集合中是否包含某一key值，通过某一key值得到value值，
     * 通过某一key删除键值对，把另一个map集合添加到此map集合，判断是否为空，清除集合，返回集合里元素的个数等常用操作。
     *
     * 通过两种方法遍历上题中的map集合
     */
    @Test
    public void map() {
        var map = new HashMap<String, Integer>();

        map.put("hello", 1);
        map.put("world", 2);

        assert map.containsKey("hello");
        assert 1 == map.get("hello");

        var valueRemoved = map.remove("hello");
        assert 1 == valueRemoved;

        assert !map.isEmpty();
        assert 1 == map.size();

        map.clear();

        var map1 = new HashMap<>(map);
        map1.put("1", 1);
        map1.put("2", 2);
        map1.put("3", 2);
        map1.put("4", 2);

        map.putAll(map1);

        assert map.equals(map1);

        System.out.println("1.----");
        map.forEach((key, value) -> System.out.println(key + "=" + value));

        System.out.println("2.----");
        var strIntEntries = map.entrySet();
        for (var entry : strIntEntries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("3.----");
        var keys = map.keySet();
        keys.forEach((key) -> System.out.println(key + "=" + map.get(key)));
    }

    /**
     * 使用Map接口的实现类完成员工工资(姓名--工资)的摸拟：
     * 1)添加几条信息
     * 2)列出所有的员工姓名
     * 3列出所有员工姓名及其工资
     * 4)删除名叫“Tom”的员工信息
     * 5)输出Jack的工资，并将其工资加1000元(通过取值实现)
     * 6)将所有工资低于1000元的员工的工资上涨20%(通过取值实现)
     */
    @Test
    public void employeeSolution() {
        var map = new HashMap<String, Double>();

        map.put("张三", 9e3);
        map.put("李四", 4e3);
        map.put("Tom", 6e3);
        map.put("Jack", 5e3);
        map.put("孙七", 100.0);

        System.out.println("姓名：");
        map.keySet().forEach(System.out::println);
        System.out.println();

        System.out.println("姓名=工资：");
        map.forEach((name, salary) -> System.out.println(name + "=" + salary));
        System.out.println();

        System.out.println("删除了：");
        System.out.println(map.remove("Tom"));
        System.out.println();

        var aSalary = map.get("Jack");
        System.out.println("Jack的工资是" + aSalary);
        map.put("Jack", aSalary + 1000);
        System.out.println();

        map.forEach((name, salary) -> {
            if (salary < 1000) map.put(name, salary * 1.2);
        });
        map.forEach((name, salary) -> System.out.println(name + "=" + salary));
    }

    /**
     * 创建有序的map集合的实例化对象，添加元素，查看排序结果
     */
    @Test
    public void sortedMap() {
        var treeMap = new TreeMap<Integer, String>();

        treeMap.put(10, "hello");
        treeMap.put(8, "world");
        treeMap.put(1, "there");

        System.out.println(treeMap);
    }

    /**
     * 封装一个新闻类，包含标题、作者、新闻内容和发布时间，新闻标题如下：
     * 新闻一：中国多地遭雾霾笼罩空气质量再成热议话题
     * 新闻二：民进党台北举行“火大游行”
     * 新闻三：春节临近北京“卖房热”
     * 新闻四：春节临近北京“卖房热”
     * 完成如下要求（共50分，每小题10分）：
     * 1）完成对新闻类的设计，要求在初始化新闻类对象时 ，通过构造传参的形式对新闻标题赋值，并要求实例化四个对象，
     * 标题内容如题。
     * 2）要求打印新闻对象时，直接打印新闻标题；
     * 3）要求使用equals方法比较新闻时，只要标题相同，就认为是同一新闻，请输出新闻一与新闻二的比较结果，
     * 新闻三与新闻四的比较结果。
     * 4）将新闻对象存入HashSet集合中，并且遍历集合，打印新闻类对象；
     * 5）打印集合中新闻数量。
     */
    @Test
    public void newsCompleted() {
        class News {
            String title;
            String author;
            String content;
            LocalDateTime dateTime;

            public News(String title) {
                this.title = title;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                News news = (News) o;
                return title.equals(news.title);
            }

            @Override
            public int hashCode() {
                return Objects.hash(title, author, content, dateTime);
            }

            @Override
            public String toString() {
                return title;
            }
        }

        News[] newsArray = {
                new News("中国多地遭雾霾笼罩空气质量再成热议话题"),
                new News("民进党台北举行“火大游行”"),
                new News("春节临近北京“卖房热”"),
                new News("春节临近北京“卖房热”")
        };

        System.out.println(newsArray[0].equals(newsArray[1]));
        System.out.println(newsArray[2].equals(newsArray[3]));

        var news = new HashSet<>(Arrays.asList(newsArray));

        news.forEach(System.out::println);
        System.out.println(news.size());
    }

    /**
     * 使用HashMap类实例化一个Map类型的对象m1，键（String类型）和值（int型）分别用于存储员工的姓名和工资，存入数据如下：
     * 张三——800元；李四——1500元；王五——3000元；
     * 1）将张三的工资更改为2600元
     * 2）为所有员工工资加薪100元；
     * 3）遍历集合中所有的员工
     * 4）遍历集合中所有的工资
     */
    @Test
    public void employeeSolution2() {
        Map<String, Integer> m1 = new HashMap<>();

        m1.put("张三", 800);
        m1.put("李四", 1500);
        m1.put("王五", 3000);
        System.out.println();
        m1.forEach((name, salary) -> System.out.println(name + "=" + salary));

        m1.put("张三", 2600);
        System.out.println();
        System.out.println("张三=" + m1.get("张三"));

        m1.forEach((name, salary) -> m1.put(name, salary + 100));
        System.out.println();
        m1.forEach((name, salary) -> System.out.println(name + "=" + salary));

        System.out.println();
        m1.keySet().forEach(System.out::println);

        System.out.println();
        m1.values().forEach(System.out::println);
    }

    /**
     * 创建一个List集合的对象，添加几个数字，反转对象中元素的顺序；根据元素的自然顺序排序；
     */
    @Test
    public void sortNumbers() {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        numbers.add(4);

        Collections.reverse(numbers);
        System.out.println(numbers);

        Collections.sort(numbers);
        System.out.println(numbers);
    }

    /**
     * 创建一个List集合的对象，添加几个字符串，反转对象中元素的顺序；根据元素的自然顺序排序；
     */
    @Test
    public void sortStrings() {
        List<String> strings = new ArrayList<>();

        strings.add("AAAA");
        strings.add("CCCC");
        strings.add("BB");
        strings.add("FFF");

        Collections.reverse(strings);
        System.out.println(strings);

        Collections.sort(strings);
        System.out.println(strings);
    }

    /**
     * 创建一个List集合的对象，添加几条数据，将1号位和2号位交换；获得最大值，最小值，
     */
    @Test
    public void swapAndMaxMin() {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(1);
        numbers.add(3);
        numbers.add(2);
        numbers.add(4);

        Collections.swap(numbers, 0, 1);
        System.out.println(numbers);

        System.out.println(Collections.max(numbers));
        System.out.println(Collections.min(numbers));
    }

    /**
     * 按要求完成如下操作
     * 生成10个随机数，值在100到200之间；
     * 将这十个数存入HashSet集合中（有可能集合的长度小于10）。
     * 将这个HashSet集合转换成ArrayList集合
     * 重新为ArrayList集合排序，按照从小到大的顺序；
     * 使用foreach遍历集合；
     */
    @Test
    public void random() {
        var random = new Random();
        Collection<Integer> randoms = new HashSet<>();

        for (int i = 0; i < 10; i++) {
            randoms.add(random.nextInt(101) + 100);
        }

        randoms = new ArrayList<>(randoms);
        Collections.sort((List<Integer>) randoms);
        for (Integer integer : randoms) {
            System.out.println(integer);
        }
    }
}
