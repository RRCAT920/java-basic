package miscalleneous.dependency_injection.email.injector;

import miscalleneous.dependency_injection.email.consumer.Consumer;
import miscalleneous.dependency_injection.email.consumer.Wechat;
import miscalleneous.dependency_injection.email.service.SmsService;

/**
 * @author huzihao
 * @since 2020/11/10 13:37
 */
public class SmsServiceInjector implements MessageServiceInjector {
    @Override
    public Consumer getConsumer() {
        return new Wechat(new SmsService());
    }
}
