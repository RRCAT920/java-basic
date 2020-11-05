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
