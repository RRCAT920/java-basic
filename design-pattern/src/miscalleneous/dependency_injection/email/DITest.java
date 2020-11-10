package miscalleneous.dependency_injection.email;

import miscalleneous.dependency_injection.email.consumer.Consumer;
import miscalleneous.dependency_injection.email.injector.EmailServiceInjector;
import miscalleneous.dependency_injection.email.injector.MessageServiceInjector;
import miscalleneous.dependency_injection.email.injector.SmsServiceInjector;

/**
 * @author huzihao
 * @since 2020/11/10 13:35
 */
public class DITest {
    public static void main(String[] args) {
        var msg = "hello";
        var email = "123@qq.com";
        var phone = "12357908";
        MessageServiceInjector injector;
        Consumer app;

        injector = new EmailServiceInjector();
        app = injector.getConsumer();
        app.processMessage(msg, email);

        System.out.println("---------------");

        injector = new SmsServiceInjector();
        app = injector.getConsumer();
        app.processMessage(msg, phone);
    }
}
