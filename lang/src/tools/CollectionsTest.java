package tools;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/9/4 21:44
 */
public class CollectionsTest {
    static final List list = new ArrayList();

    @Before
    public void init() {
        list.add(7);
        list.add(5);
        list.add(3);
        list.add(1);

        assert "[7, 5, 3, 1]".equals(list.toString());
    }

    @Test
    public void testReverse() {
        Collections.reverse(list);
        assert "[1, 3, 5, 7]".equals(list.toString());
    }

    @Test
    public void testShuffle() {
        Collections.shuffle(list);
        System.out.println(list);
    }

    @Test
    public void testSort() {
        Collections.sort(list);

        assert "[1, 3, 5, 7]".equals(list.toString());

        Collections.sort(list, ((o1, o2) -> {
            if (o1 instanceof Integer && o2 instanceof Integer) {
                return -((Integer) o1 - (Integer) o2);
            }
            throw new RuntimeException("类型错误❌");
        }));

        assert "[7, 5, 3, 1]".equals(list.toString());
    }

    @Test
    public void testSwap() {
        Collections.swap(list, 0, 1);

        assert "[5, 7, 3, 1]".equals(list.toString());
    }

    @Test
    public void testMaxAndMin() {
        assert Collections.max(list).equals(7);
        assert Collections.min(list).equals(1);
    }

    @Test
    public void testFrequency() {
        assert 1 == Collections.frequency(list, list.get(0));
    }

    @Test
    public void testCopy() {
        List bugDest = new ArrayList();
        boolean executed = false;

        try {
            Collections.copy(bugDest, list);
        } catch (IndexOutOfBoundsException e) {
            executed = true;
        }
        assert executed;

        /*
        避免ArrayList的懒加载
         */
        var dest = Arrays.asList(new Object[list.size()]);
        Collections.copy(dest, list);
        assert list.equals(dest);
    }

    @Test
    public void testReplaceAll() {
        Collections.replaceAll(list, 7, 9);

        assert "[9, 5, 3, 1]".equals(list.toString());
    }
}
