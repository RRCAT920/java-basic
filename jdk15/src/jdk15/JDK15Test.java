package jdk15;

/**
 * @author huzihao
 * @since 2020/10/1 08:06
 */
public class JDK15Test {

}

abstract sealed class A permits B, C, D {
    public abstract void run();
}

non-sealed class B extends A {
    @Override
    public void run() {

    }
}

non-sealed class C extends A {
    @Override
    public void run() {

    }
}

non-sealed abstract class D extends A {

}

class E extends D {
    @Override
    public void run() {

    }
}

sealed interface foo permits bar {
    void run();
}

non-sealed interface bar extends foo {

}
