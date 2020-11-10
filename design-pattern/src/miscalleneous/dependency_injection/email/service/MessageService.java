package miscalleneous.dependency_injection.email.service;

/**
 * @author huzihao
 * @since 2020/11/10 13:33
 */
public interface MessageService {
    void sendMessage(String message, String receiver);
}
