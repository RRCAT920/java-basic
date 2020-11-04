package behavioral_type.mediator;

/**
 * @author huzihao
 * @since 2020/11/4 23:04
 */
public interface ChatMediator {
    void addUser(User user);

    void sendMessage(String msg, User user);
}
