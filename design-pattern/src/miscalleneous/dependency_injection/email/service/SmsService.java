package miscalleneous.dependency_injection.email.service;

/**
 * @author huzihao
 * @since 2020/11/10 13:36
 */
public class SmsService implements MessageService {
    @Override
    public void sendMessage(String message, String receiver) {
        System.out.println("发送短信" + message + "给" + receiver);
    }
}
