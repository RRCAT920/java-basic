package module;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author huzihao
 * @since 2020/9/14 23:56
 */
public class CollectionFactoryTest {
    /**
     * 8
     */
    @Test
    public void unmodifiableMethod() {
        var numbers = Arrays.asList(1, 2, 3, 4, 5);
        var numbersReadOnly = Collections.unmodifiableList(numbers);
        try {
            //noinspection ConstantConditions
            numbersReadOnly.add(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(numbersReadOnly);
    }

    /**
     * 9
     */
    @Test
    public void ofMethod() {
        var numbers = List.of(1, 2, 3);
        try {
            //noinspection ConstantConditions
            numbers.add(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        var numberSet = Set.of(1, 2, 3);
        try {
            //noinspection ConstantConditions
            numberSet.add(1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        var strNumberMap = Map.of("hello", 1, "tom", 2);
        try {
            //noinspection ConstantConditions
            strNumberMap.put("Beita", 3);
        } catch (Exception e) {
            e.printStackTrace();
        }

        var strNumberMapWithEntries = Map.ofEntries(
                Map.entry("hello", 1), Map.entry("tom", 2));
        try {
            //noinspection ConstantConditions
            strNumberMapWithEntries.put("Shuke", 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
