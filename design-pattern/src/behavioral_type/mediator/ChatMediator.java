package behavioral_type.mediator;

/**
 * 聊天室的中介者都要实现该接口。
 *
 * @author huzihao
 * @since 2020/11/4 23:04
 */
public interface ChatMediator {
    /**
     * 添加要通信的用户
     *
     * @param user 要通信的用户
     */
    void addUser(User user);

    /**
     * 发送信息给要通信的用户
     *
     * @param msg  信息
     * @param user 要通信的用户
     */
    void sendMessage(String msg, User user);
}
