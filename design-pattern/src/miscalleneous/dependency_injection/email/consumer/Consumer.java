package miscalleneous.dependency_injection.email.consumer;

/**
 * @author huzihao
 * @since 2020/11/10 13:34
 */
public interface Consumer {
    void processMessage(String message, String receiver);
}
