package generic;

import org.junit.Test;

import java.util.*;

/**
 * @author huzihao
 * @since 2020/9/5 12:14
 */
public class GenericTest {
    /**
     * 泛型：定义类、接口时通过一个标识表示属性的类型、返回值的类型，参数的类型，这个类型参数在使用时确定✅。
     * （默认类型参数是java.lang.Object，类型参数必须是类，不能是基本数据类型）
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @Test
    public void genericOverview() {
        var arrayList = new ArrayList();
        boolean hasClassCastException = false;

        // 类型不安全🔐
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add("3");

        for (Object o : arrayList) {
            try {
                // 导致强转不安全🔐
                int value = (Integer) o;
            } catch (ClassCastException e) {
                hasClassCastException = true;
            }
        }

        assert hasClassCastException;

        // 解决类型安全问题，从而解决强转安全问题
        var numbers = new ArrayList<Integer>();

        numbers.add(1);
        numbers.add(2);
//        Required type Integer
//        Provided String
//        numbers.add("3");
        numbers.add(3);

        for (var number : numbers) {

        }
    }

    /**
     * 类型推断：type inference (7.0)
     */
    @Test
    public void typeInfer() {
        ArrayList<Integer> list = new ArrayList<>();

        TreeSet<Integer> treeSet = new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        });
    }

    /**
     * 定义一个 Employee 类，
     * 该类包含:private 成员变量 name,age,birthday，其中 birthday 为 MyDate 类的 对象;
     * 并为每一个属性定义 getter, setter 方法;
     * 并重写 toString 方法输出 name, age, birthday
     * MyDate 类包含:
     * private 成员变量 month,day,year;并为每一个属性定义 getter, setter 方法;
     * 创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中(TreeSet 需使用泛型 来定义)，
     * 分别按以下两种方式对集合中的元素进行排序，并遍历输出:
     * 1). 使 Employee 继承 Comparable 接口，并按 name 排序
     * 2). 创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序。
     */
    @Test
    public void genericTreeSet() {
        class MyDate implements Comparable<MyDate> {
            final int year;
            final int month;
            final int day;

            public MyDate(int year, int month, int day) {
                this.year = year;
                this.month = month;
                this.day = day;
            }

            @Override
            public String toString() {
                return "MyDate{" +
                        "year=" + year +
                        ", month=" + month +
                        ", day=" + day +
                        '}';
            }

            @Override
            public int compareTo(MyDate o) {
                int diffYear = year - o.year;
                if (diffYear != 0) return diffYear;

                int diffMonth = month - o.month;
                if (diffMonth != 0) return diffMonth;

                return day - o.day;
            }
        }

        class Employee implements Comparable<Employee> {
            final String name;
            final int age;
            final MyDate birthday;

            public Employee(String name, int age, MyDate birthday) {
                this.name = name;
                this.age = age;
                this.birthday = birthday;
            }

            @Override
            public int compareTo(Employee o) {
                return this.name.compareTo(o.name);
            }

            @Override
            public String toString() {
                return "Employee{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        ", birthday=" + birthday +
                        '}';
            }
        }

        var ageCOMP = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.birthday.compareTo(o2.birthday);
            }
        };

        var treeSet = new TreeSet<Employee>();
        treeSet.add(new Employee("张三", 20, new MyDate(1999, 1, 2)));
        treeSet.add(new Employee("李四", 19, new MyDate(2000, 1, 2)));
        treeSet.add(new Employee("王五", 18, new MyDate(2001, 1, 2)));
        treeSet.add(new Employee("赵六", 18, new MyDate(2001, 2, 2)));
        treeSet.add(new Employee("孙七", 18, new MyDate(2001, 1, 5)));

        System.out.println(treeSet);

        var treeSet1 = new TreeSet<>(ageCOMP);
        treeSet1.addAll(treeSet);
        System.out.println(treeSet1);
    }

    /**
     * 定义个泛型类 DAO<T>，在其中定义一个 Map 成员变量，Map 的键
     * 为 String 类型，值为 T 类型。
     * 分别创建以下方法:
     * public void save(String id,T entity): 保存 T 类型的对象到 Map 成员 变量中
     * public T get(String id):从 map 中获取 id 对应的对象
     * public void update(String id,T entity):替换 map 中 key 为 id 的内容, 改为 entity 对象
     * public List<T> list():返回 map 中存放的所有 T 对象
     * public void delete(String id):删除指定 id 对象
     * 定义一个 User 类: 该类包含:private成员变量(int类型)id，age;(String 类型)name。
     * 定义一个测试类:
     * 创建 DAO 类的对象，分别调用其 save、get、update、list、delete 方 法来操作 User 对象，
     * 使用 Junit 单元测试类进行测试。
     */
    @Test
    public void testUserDAO() {
        var dao = new DAO<User>();
        dao.save("1", new User(1, 11, "张三"));
        dao.save("2", new User(2, 12, "李四"));
        dao.save("3", new User(3, 13, "王五"));

        dao.list().forEach(System.out::println);
        System.out.println("---------");

        dao.update("1", new User(1, 30, "方文山"));

        dao.list().forEach(System.out::println);
        System.out.println("---------");

        dao.delete("2");
        dao.list().forEach(System.out::println);
    }

    /**
     * <p>按要求完成如下操作</p>
     * <ol>
     *     <li>封装一个汽车类，包含String  name、int  speed属性，
     *     在测试类中实例化三个对象：c1，c2，c3，分别设置name为：“奥拓”，“宝马”，“奔驰”，
     *     速度分别设置为：100,200,300</li>
     *     <li>使用Map集合对象m1将这三个汽车类对象保存成key，然后将int型的汽车价钱作为值保存在m1的value中，
     *     上述三款汽车分别对应的价钱是10000,500000,2000000</li>
     *     <li>遍历m1的键，打印name属性</li>
     *     <li>通过合适的方法，求出m1中“宝马”的价格，并打印结果</li>
     *     <li>经过折旧，所有汽车都降价到原来的80%，请打印降价后“宝马”的价格</li>
     * </ol>
     */
    @Test
    public void car() {
        class Car {
            String name;
            int speed;

            public Car(String name, int speed) {
                this.name = name;
                this.speed = speed;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Car car = (Car) o;
                return speed == car.speed &&
                        Objects.equals(name, car.name);
            }

            @Override
            public int hashCode() {
                return Objects.hash(name, speed);
            }
        }

        Map<Car, Integer> m1 = new HashMap<>();
        var car1 = new Car("奥拓", 100);
        var car2 = new Car("宝马", 200);
        var car3 = new Car("奔驰", 300);

        m1.put(car1, (int) 1e4);
        m1.put(car2, (int) 5e5);
        m1.put(car3, (int) 2e6);

        m1.keySet().forEach((key) -> System.out.println(key.name));
        System.out.println();

        System.out.println(m1.get(car2));
        System.out.println();

        m1.forEach((key, value) -> m1.put(key, (int) (value * 0.8)));
        System.out.println(m1.get(car2));
        System.out.println();
    }

    /**
     * <p>按要求完成如下操作 </p>
     * <ol>
     *     <li>要求集合对象c1中，只能添加字符串形式的单个元素，元素可以重复，在测试类中为c1集合添加字符串
     *     “这是一个可以重复的集合”三遍，然后遍历打印结果。</li>
     *     <li>要求集合对象c2中只能添加整型数值，并且不可重复，按自然顺序排序。要求遍历集合对象，
     *     打印添加进1,2,3,4,5五个数字的c2集合</li>
     *     <li>要求创建一个合适的Map集合对象m1，它的键和值都只能是字符串，并且值可以是null，
     *     像map集合中添加三组字符串，其中一个只有键，值是空，遍历这个集合对象的键，并打印键。</li>
     *     <li>想办法将m1中所有值为null都替换成一个字符串”这里是空值”</li>
     *     <li>遍历m1的所有值。</li>
     * </ol>
     */
    @Test
    public void testCollAndMap() {
        Collection<String> c1 = new ArrayList<>();

        c1.add("这是一个可以重复的集合");
        c1.add("这是一个可以重复的集合");
        c1.add("这是一个可以重复的集合");

        c1.forEach(System.out::println);

        Collection<Integer> c2 = new TreeSet<>();

        c2.add(1);
        c2.add(2);
        c2.add(3);
        c2.add(4);
        c2.add(5);

        System.out.println();
        c2.forEach(System.out::println);

        Map<String, String> m1 = new HashMap<>();

        m1.put("hello", "world");
        m1.put("hi", "there");
        m1.put("null", null);

        System.out.println();
        m1.keySet().forEach(System.out::println);

        m1.forEach((key, value) -> m1.put(key, value == null ? "这里是空值" : value));

        System.out.println();
        m1.values().forEach(System.out::println);
    }

    /**
     * <p>开发一个泛型Apple类，要求有一个重量属性weight在测试类中实例化不同的泛型对象，
     * 要求对象a1的这一属性是String类型，对象a2的这一属性是Integer型，a3的这一属性是Double型。
     * 分别为a1，a2，a3的重量属性赋值为：”500克”，500,500.0，在测试类中通过对象调用访问器得到属性值并输出。
     * 另外思考，为什么a2和a3的属性需要是Integer和Double而不是int和double？</p>
     */
    @Test
    public void testApple() {
        class Apple<T> {
            final T weight;

            public Apple(T weight) {
                this.weight = weight;
            }

            public T getWeight() {
                return weight;
            }
        }

        var a1 = new Apple<>("500克");
        var a2 = new Apple<>(500);
        var a3 = new Apple<>(500.0);

        System.out.println(a1.getWeight());
        System.out.println(a2.getWeight());
        System.out.println(a3.getWeight());
    }

    /**
     * <p>
     *     封装一个新闻类News，包含新闻标题，新闻作者，新闻内容，新闻类型四个属性，提供必要的访问器和修改器方法，
     *     重写toString方法，要求打印对象时输出格式为“标题；类型；作者”，要求只要新闻标题相同就判断为同一条新闻。
     *     在测试类中创建一个只能容纳该类对象的ArrayList集合，添加三条新闻。遍历集合，打印新闻标题，
     *     将新闻标题截取字符串到10个汉字的长度。
     * </p>
     */
    @Test
    public void testNews() {
        class News {
            String title;
            String author;
            String content;
            String type;

            public News(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return title + "; " + type + "; " + author;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                News news = (News) o;
                return Objects.equals(title, news.title);
            }

            @Override
            public int hashCode() {
                return Objects.hash(title);
            }
        }

        var arrayList = new ArrayList<News>();

        arrayList.add(new News("中国多地遭雾霾笼罩空气质量再成热议话题"));
        arrayList.add(new News("民进党台北举行“火大游行”"));
        arrayList.add(new News("春节临近北京“卖房热”"));

        arrayList.forEach((e) -> {
            var title = e.title;
            var result = title.length() <= 10 ? title : title.substring(0, 10);
            System.out.println(result);
        });
    }

    /**
     *  <p>按要求完成下列任务</p>
     *  <ol>
     *      <li>使用HashMap类实例化一个Map类型的对象m1，键（String类型）和值（int型）分别用于存储员工的姓名和工资，
     *      存入数据如下：	张三——800元；李四——1500元；王五——3000元；</li>
     *      <li>将张三的工资更改为2600元</li>
     *      <li>为所有员工工资加薪100元</li>
     *      <li>遍历集合中所有的员工</li>
     *      <li>遍历集合中所有的工资</li>
     *  </ol>
     */
    @Test
    public void employeeSalary() {
        Map<String, Integer> m1 = new HashMap<>();

        m1.put("张三", 800);
        m1.put("李四", 1500);
        m1.put("王五", 3000);

        var integer = m1.put("张三", 2600);
        assert null == integer || 800 == integer;

        m1.forEach((key, value) -> m1.put(key, value + 100));

        m1.keySet().forEach(System.out::println);

        m1.values().forEach(System.out::println);
    }
}

class DAO<E> {
    private final Map<String, E> map = new HashMap<>();

    public void save(String id, E entity) {
        map.put(id, entity);
    }

    public E get(String id) {
        return map.get(id);
    }

    public void update(String id, E entity) {
        map.putIfAbsent(id, entity);
    }

    public List<E> list() {
        return new ArrayList<>(map.values());
    }

    public void delete(String id) {
        map.remove(id);
    }
}

class User {
    private final int id;
    private final int age;
    private final String name;

    public User(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                age == user.age &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, age, name);
    }
}