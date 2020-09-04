package map;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author huzihao
 * @since 2020/9/3 22:37
 */
public class MapTest {
    @Test
    public void HashMapVSHashtable() {
        Map map = new Hashtable();

        try {
            map.put(1, null);
        } catch (NullPointerException e) {
            System.out.println("key或value不能是null");
        }

        map = new HashMap();
        map.put(null, null);
    }

    @Test
    public void testForAddAndRemove() {
        var map = new HashMap();

        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 123);
        map.put("AA", 87);

        System.out.println(map);

        var anotherMap = new HashMap();

        anotherMap.put("Hello", "World");
        anotherMap.putAll(map);

        System.out.println(anotherMap);

        assert map.remove(45).equals(123);
        Object o = map.remove(45);
        assert o == null;

        map.clear();
        assert map.isEmpty();
        assert "{}".equals(map.toString());
    }

    @Test
    public void testForElementQuery() {
        var map = new HashMap();

        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 123);

        assert map.get("AA").equals(123);
        assert null == map.get("CC");

        assert map.containsKey("AA");
        assert !map.containsKey("CC");

        assert map.containsValue(123);
        assert !map.containsValue(1231212);

        map.values().forEach(System.out::println);
        map.keySet().forEach(System.out::println);
        map.entrySet().forEach(System.out::println);
    }

    @Test
    public void traverse() {
        var map = new HashMap();

        map.put("AA", 123);
        map.put(45, 123);
        map.put("BB", 123);

        var keyItor = map.keySet().iterator();
        while (keyItor.hasNext()) {
            var key = keyItor.next();
            System.out.println(key + "=" + map.get(key));
        }

        var entryItor = map.entrySet().iterator();
        while (entryItor.hasNext()) {
            var entry = (Map.Entry) entryItor.next();
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
    }

    /**
     * <h3>TreeMap</h3>
     * <ol>
     *     <li>key的类型必须相同</li>
     *     <li>按key自动排序</li>
     *     <li>底层用 红黑树🌈👋🌲 实现</li>
     * </ol>
     */
    @Test
    public void TreeMapWithComparable() {
        var treeMap = new TreeMap();

        treeMap.put("B", 2);
        treeMap.put("C", 3);
        treeMap.put("F", 4);
        treeMap.put("A", 1);

        assert "{A=1, B=2, C=3, F=4}".equals(treeMap.toString());
    }

    @Test
    public void TreeMapWithComparator() {
        /*
          含有属性name和age的User类，重写toString()方法
         */
        class User {
            String name;
            int age;

            public User(String name, int age) {
                this.name = name;
                this.age = age;
            }

            /**
             * @return "{value of name,value of age}"
             */
            @Override
            public String toString() {
                return "{" + name + "," + age + "}";
            }
        }

        var ageComparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof User && o2 instanceof User) {
                    return ((User) o1).age - ((User) o2).age;
                }
                throw new RuntimeException("类型不匹配");
            }
        };

        var treeMap = new TreeMap(ageComparator);

        treeMap.put(new User("张三", 112), 72);
        treeMap.put(new User("李四", 16), 72);
        treeMap.put(new User("王五", 332), 72);
        treeMap.put(new User("刘六", 76), 72);

        assert "{{李四,16}=72, {刘六,76}=72, {张三,112}=72, {王五,332}=72}".equals(treeMap.toString());
    }

    @Test
    public void testForProperties() {
        var properties = new Properties();

        try (var fistream = new FileInputStream("jdbc.properties")) {
            properties.load(fistream);

            assert "Tom".equals(properties.getProperty("name"));
            assert "1234567".equals(properties.getProperty("password"));
        } catch (IOException e) {
            // template code
            e.printStackTrace();
        }
    }
}
