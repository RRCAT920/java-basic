package behavioral_type.state.tv_remote;

/**
 * @author huzihao
 * @since 2020/11/9 21:32
 */
public class TVStartState implements State {
    @Override
    public void doAction() {
        System.out.println("打开电视机");
    }
}
