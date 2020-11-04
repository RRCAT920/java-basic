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
        // 通过所属中介者发送消息给其他用户
        chatMediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg) {
        System.out.println(name + ": Receiving Message=" + msg);
    }
}
