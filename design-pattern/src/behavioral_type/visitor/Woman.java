package behavioral_type.visitor;

/**
 * @author huzihao
 * @since 2020/11/3 16:55
 */
public class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomenResult(this);
    }
}
