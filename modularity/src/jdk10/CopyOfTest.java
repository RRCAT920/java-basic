package jdk10;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/9/15 01:53
 */
public class CopyOfTest {
    @Test
    public void copyOfMethod() {
        var list = new ArrayList<String>();
        var copyList = List.copyOf(list);
        assert list != copyList;

        var readOnlyList = List.of(1, 2, 3);
        var copyReadOnlyList = List.copyOf(readOnlyList);
        assert readOnlyList == copyReadOnlyList;
    }
}
