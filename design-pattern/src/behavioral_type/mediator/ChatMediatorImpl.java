package behavioral_type.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/4 23:11
 */
public class ChatMediatorImpl implements ChatMediator {
    // 未考虑用户相同的情况
    // 持有一个用户集合，用于与用户通信
    private final List<User> userList;

    public ChatMediatorImpl() {
        userList = new ArrayList<>();
    }

    /**
     * 添加要通信的用户
     *
     * @param user 要通信的用户
     */
    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    /**
     * 发送信息给除自己以外的其他用户
     *
     * @param msg  信息
     * @param user 要通信的用户
     */
    @Override
    public void sendMessage(String msg, User user) {
        for (var u : userList) {
            if (u != user) u.receive(msg);
        }
    }
}
