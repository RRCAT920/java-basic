package miscalleneous.dependency_injection.email.injector;

import miscalleneous.dependency_injection.email.consumer.Consumer;

/**
 * @author huzihao
 * @since 2020/11/10 13:34
 */
public interface MessageServiceInjector {
    Consumer getConsumer();
}
