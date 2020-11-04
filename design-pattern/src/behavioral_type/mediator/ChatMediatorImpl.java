package behavioral_type.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/4 23:11
 */
public class ChatMediatorImpl implements ChatMediator {
    // 未考虑用户相同的情况
    private final List<User> userList;

    public ChatMediatorImpl() {
        userList = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        userList.add(user);
    }

    @Override
    public void sendMessage(String msg, User user) {
        for (var u : userList) {
            if (u != user) u.receive(msg);
        }
    }
}
