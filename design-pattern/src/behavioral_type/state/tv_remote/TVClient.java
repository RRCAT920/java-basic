package behavioral_type.state.tv_remote;

/**
 * @author huzihao
 * @since 2020/11/9 21:31
 */
public class TVClient {
    public static void main(String[] args) {
        var tvContext = new TVContext();

        tvContext.setState(new TVStartState());
        tvContext.doAction();

        tvContext.setState(new TVStopState());
        tvContext.doAction();
    }
}
