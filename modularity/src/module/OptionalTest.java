package module;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author huzihao
 * @since 2020/9/15 00:57
 */
public class OptionalTest {
    private static final Random RANDOM = new Random();
    @Test
    public void streamMethod() {
        var secret = RANDOM.nextInt(2);
        var list = 0 == secret ?
                List.of(1, 2, 3, 4) : null;
        var optList = Optional.ofNullable(list);
        var array = optList.stream()
                .flatMap(Collection::stream)
                .toArray();

        System.out.println(secret); // for check
        var arrayStr = Arrays.toString(array);
        if (0 == secret) assert "[1, 2, 3, 4]".equals(arrayStr);
        else assert "[]".equals(arrayStr);
    }
}
