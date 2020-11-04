package behavioral_type.mediator.util;

/**
 * 所用同事类都要继承Colleague抽象类。该类实现了将自己交给中介者管理的构造器以及发送信息和接受信息的抽象方法。
 *
 * @author huzihao
 * @since 2020/11/5 00:00
 */
public abstract class Colleague {
    protected Mediator mediator;

    /**
     * 子类要重写此构造器，以初始化所属中介者和将自己交给中介者管理。
     *
     * @param mediator 所属中介者
     */
    public Colleague(Mediator mediator) {
        this.mediator = mediator;
        mediator.addColleague(this);
    }

    /**
     * 子类要重写此方法，以实现发送信息的具体逻辑
     *
     * @param o 要发送的信息
     */
    public abstract void send(Object o);

    /**
     * 子类要重写此方法，以实现接受信息的具体逻辑
     *
     * @param o 要接受的信息
     */
    public abstract void receive(Object o);
}
