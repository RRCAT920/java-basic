package behavioral_type.mediator;

/**
 * @author huzihao
 * @since 2020/11/4 23:14
 */
public class Client {
    public static void main(String[] args) {
        ChatMediator chatMediator = new ChatMediatorImpl();
        var user1 = new UserImpl(chatMediator, "user1");
        var user2 = new UserImpl(chatMediator, "user2");
        var user3 = new UserImpl(chatMediator, "user3");
        var user4 = new UserImpl(chatMediator, "user4");

        user1.send("Hi!");
    }
}
