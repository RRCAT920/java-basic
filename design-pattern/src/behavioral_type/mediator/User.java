package behavioral_type.mediator;

/**
 * @author huzihao
 * @since 2020/11/4 23:05
 */
public abstract class User {
    protected ChatMediator chatMediator;
    protected String name;

    public User(ChatMediator chatMediator, String name) {
        this.chatMediator = chatMediator;
        this.name = name;

        chatMediator.addUser(this);
    }

    public abstract void send(String msg);

    public abstract void receive(String msg);
}
