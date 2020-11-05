package jdk8;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.Optional;

import jdk8.data.Father;
import jdk8.data.Son;

/**
 * @author huzihao
 * @since 2020/9/14 12:14
 */
public class OptionalTest {
    @Test
    public void optionalBasicExample() {
        System.out.println(division(1, 0));
        System.out.println(division(2, 2));
    }

    public static Optional<Integer> division(Integer i1, Integer i2) {
        if (0 == i2) return Optional.empty();
        return Optional.of(i1 / i2);
    }

    @Test
    public void jdk8OptionalMethods() {
        // jdk8
        var opt1 = division(2, 1);
        opt1.ifPresent(System.out::println);

        var opt2 = division(2, 0);
        opt2.ifPresent(System.out::println);

        System.out.println("Opt2没有值的情况下，默认值为" + opt2.orElse(0));

        if (opt2.isPresent()) {
            System.out.println("Opt2有值");
        } else {
            System.out.println("Opt2没有值");
        }
    }

    @Test
    public void jdk9OptionalMethods() {
        var opt1 = division(2, 1);
        var opt2 = division(2, 0);

        // ifPresentOrElse
        opt1.ifPresentOrElse(System.out::println, () -> System.out.println("没有值"));

        opt2.ifPresentOrElse(System.out::println, () -> System.out.println("没有值"));

        // or
        System.out.println(opt2.or(() -> Optional.of(0)));
    }

    @Test
    public void testGetSonName() {
        System.out.println(getSonName(null));
        System.out.println(getSonName(new Father()));
        System.out.println(getSonName(new Father("舒克", new Son("贝塔"))));
    }

    private static String getSonName(Father father) {
        return Optional.of(father)
                .map(Father::getSon)
                .orElseThrow().getName();
    }

    @Test
    public void testGetSonNameWithAssert() {
        System.out.println(getSonNameWithAssert(new Father()));
    }

    private String getSonNameWithAssert(Father father) {
        assert null != father;
        var son = father.getSon();
        assert null != son;
        return son.getName();
    }

    @Test
    public void testGetSonNameWithAnnotation() {
        System.out.println(getSonNameWithAnnotation(new Father()));
    }

    private String getSonNameWithAnnotation(@NotNull Father father) {
        @NotNull var son = father.getSon();
        return son.getName();
    }
}
