package behavioral_type.state.tv_remote;

/**
 * @author huzihao
 * @since 2020/11/9 21:31
 */
public class TVContext implements State {
    private State state;

    public void setState(State state) {
        this.state = state;
    }
    
    @Override
    public void doAction() {
        state.doAction();
    }
}
