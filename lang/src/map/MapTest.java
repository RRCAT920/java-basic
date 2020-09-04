package map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

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
}
