package behavioral_type.visitor;

/**
 * @author huzihao
 * @since 2020/11/3 16:54
 */
public class Man extends Person {
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}
