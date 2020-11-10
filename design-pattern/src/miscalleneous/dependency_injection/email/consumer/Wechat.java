package miscalleneous.dependency_injection.email.consumer;

import miscalleneous.dependency_injection.email.service.MessageService;

/**
 * @author huzihao
 * @since 2020/11/10 13:37
 */
public class Wechat implements Consumer {
    private MessageService service;

    public Wechat(MessageService service) {
        this.service = service;
    }

    @Override
    public void processMessage(String message, String receiver) {
        service.sendMessage(message, receiver);
    }
}
