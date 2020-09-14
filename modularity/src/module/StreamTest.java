package module;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author huzihao
 * @since 2020/9/15 00:25
 */
public class StreamTest {
    @Test
    public void takeAndDrop() {
        var numbers = List.of(1, 2, 30, 50, 20, 30, 10);
        var takeArr = numbers.stream()
                .takeWhile(number -> number < 50)
                .toArray();
        assert "[1, 2, 30]".equals(Arrays.toString(takeArr));

        var dropArr = numbers.stream()
                .dropWhile(number -> number < 50)
                .toArray();
        assert "[50, 20, 30, 10]".equals(Arrays.toString(dropArr));
    }

    @Test
    public void streamOfMethod() {
        var count = Stream.of(1, 2, null).count();
        //noinspection ConstantConditions
        assert 3 == count;

        try {
            //不能只存储一个null
            @SuppressWarnings({"ConstantConditions", "ConfusingArgumentToVarargsMethod"})
            var stream = Stream.of(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        var i = 10;
        var nullableStream = Stream.ofNullable(i);
        assert 1 == nullableStream.count();

        Integer i1 = null;
        var nullableStream1 = Stream.ofNullable(i1);
        assert 0 == nullableStream1.count();

        var n = new Random().nextInt(2);
        var i2 = n == 0 ? null : n;

        // 不确定的断言
        assert 0 == Stream.ofNullable(i2).count();
    }

    @Test
    public void iterate() {
        var arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }

        var array = Stream.iterate(0, i -> i < 100, i -> i + 1).toArray();
        assert Arrays.toString(array).equals(Arrays.toString(arr));
    }
}
