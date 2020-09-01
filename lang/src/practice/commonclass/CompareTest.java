package practice.commonclass;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author huzihao
 * @since 2020/8/27 01:10
 */
public class CompareTest {

    @Test
    public void testPersonSorting() {
        class Person implements Comparable<Person> {
            public Person(String name, int age) {
                this.name = name;
                this.age = age;
            }

            private String name;
            private int age;

            @Override
            public int compareTo(Person o) {
                return name.compareTo(o.name);
            }

            @Override
            public String toString() {
                return "Person{" +
                        "name='" + name + '\'' +
                        ", age=" + age +
                        '}';
            }
        }

        var people = new Person[3];
        people[0] = new Person("李四", 13);
        people[1] = new Person("王武", 123);
        people[2] = new Person("刘六", 32);

        System.out.println("自然排序：⬇️");
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));

        var people1 = new Person[3];
        people1[0] = new Person("李四", 13);
        people1[1] = new Person("王武", 123);
        people1[2] = new Person("刘六", 32);

        System.out.println("定制排序：⬇️");
        Arrays.sort(people1, (person1, person2) -> -Integer.compare(person1.age, person2.age));
        System.out.println(Arrays.toString(people1));
    }
}
