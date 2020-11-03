package behavioral_type.visitor;

/**
 * @author huzihao
 * @since 2020/11/3 16:54
 */
public abstract class Person {
    public abstract void accept(Action action);
}
