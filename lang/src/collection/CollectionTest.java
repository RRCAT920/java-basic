package collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

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
}
