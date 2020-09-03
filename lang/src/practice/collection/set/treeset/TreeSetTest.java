package practice.collection.set.treeset;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author huzihao
 * @since 2020/9/3 20:18
 */
public class TreeSetTest {
    /**
     * 1. 定义一个 Employee 类。
     * 该类包含:private 成员变量 name,age,birthday，其中 birthday 为 MyDate 类的对象;
     * 并为每一个属性定义 getter, setter 方法;
     * 并重写 toString 方法输出 name, age, birthday
     * MyDate 类包含:
     * private 成员变量 year,month,day;并为每一个属性定义 getter, setter 方法;
     * 创建该类的 5 个对象，并把这些对象放入 TreeSet 集合中(下一章: TreeSet 需使用泛型来定义) 分别按以下两种方式对集合中的元素进行排序，并遍历输出:
     * 1). 使 Employee 实现 Comparable 接口，并按 name 排序
     * 2). 创建 TreeSet 时传入 Comparator 对象，按生日日期的先后排序。
     */
    @Test
    public void testTreeSet() {
        var set = new TreeSet();
        set.add(new Employee("E", 13, new MyDate(2000, 1, 1)));
        set.add(new Employee("D", 13, new MyDate(2000, 1, 2)));
        set.add(new Employee("C", 13, new MyDate(2001, 3, 1)));
        set.add(new Employee("B", 13, new MyDate(2001, 2, 1)));
        set.add(new Employee("A", 13, new MyDate(2000, 5, 6)));

        set.forEach(System.out::println);

        var birthdayComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Employee && o2 instanceof Employee) {
                    var date1 = ((Employee) o1).getBirthday();
                    var date2 = ((Employee) o2).getBirthday();

                    int diffYear = date1.getYear() - date2.getYear();
                    if (diffYear != 0) return diffYear;

                    int diffMonth = date1.getMonth() - date2.getMonth();
                    if (diffMonth != 0) return diffMonth;

                    return date1.getDay() - date2.getDay();
                }
                throw new RuntimeException("类型错误");
            }
        };
        var ageSet = new TreeSet(birthdayComparator);
        ageSet.addAll(set);
        System.out.println("------------");
        ageSet.forEach(System.out::println);
    }
}

class MyDate {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
}

class Employee implements Comparable {
    private String name;
    private int age;
    private MyDate birthday;

    public Employee(String name, int age, MyDate birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Employee) {
            var e = (Employee) o;
            return this.name.compareTo(e.name);
        }
        throw new RuntimeException("类型不匹配");
    }
}
