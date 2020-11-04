package behavioral_type.mediator;

/**
 * @author huzihao
 * @since 2020/11/4 23:09
 */
public class UserImpl extends User {
    public UserImpl(ChatMediator chatMediator, String name) {
        super(chatMediator, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(name + ": Sending Message=" + msg);
        chatMediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(name + ": Receiving Message=" + msg);
    }
}
