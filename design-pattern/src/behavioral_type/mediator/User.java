package behavioral_type.mediator;

/**
 * 所有用户都要继承的抽象类。<p></p>
 * 持有一个中介者对象，通过该对象发送信息给其他用户。<p></p>
 * 实现一个构造器，用于初始化姓名和自己的中介者，以及将自己交给中介者管理。<p></p>
 *
 * @author huzihao
 * @since 2020/11/4 23:05
 */
public abstract class User {
    protected ChatMediator chatMediator;
    protected String name;

    /**
     * 所用用户都要重写此方法，以初始化自己的中介者和姓名，同时将自己交给中介者管理。
     *
     * @param chatMediator 所属中介者
     * @param name         用户姓名
     */
    public User(ChatMediator chatMediator, String name) {
        this.chatMediator = chatMediator;
        this.name = name;

        chatMediator.addUser(this);
    }

    /**
     * 发送消息
     *
     * @param msg 消息
     */
    public abstract void send(String msg);

    /**
     * 接受消息
     *
     * @param msg 消息
     */
    public abstract void receive(String msg);
}
