package behavioral_type.mediator.util;

/**
 * 所有中介者都要实现Mediator接口。该接口定义添加同事和发送信息的规范。
 *
 * @author huzihao
 * @since 2020/11/4 23:56
 */
public interface Mediator {
    /**
     * 添加要通信的同事
     *
     * @param colleague 同事
     */
    void addColleague(Colleague colleague);

    /**
     * 发送信息给各同事
     *
     * @param o         要发送的信息
     * @param colleague 同事
     */
    void send(Object o, Colleague colleague);
}
