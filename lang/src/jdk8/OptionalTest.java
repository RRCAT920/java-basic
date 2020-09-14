package jdk8;

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
    public void testSon() {
        System.out.println(getSonName(null));
        System.out.println(getSonName(new Father()));
        System.out.println(getSonName(new Father("舒克", new Son("贝塔"))));
    }

    private String getSonName(Father father) {
        var fatherOpt = Optional.ofNullable(father)
                .orElse(new Father("小头爸爸", new Son("大头儿子")));
        return Optional.ofNullable(fatherOpt.getSon())
                .orElse(new Son("黑猫警长"))
                .getName();
    }
}
