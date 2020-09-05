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