package miscalleneous.dependency_injection.email.consumer;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import miscalleneous.dependency_injection.email.injector.MessageServiceInjector;

/**
 * @author huzihao
 * @since 2020/11/10 13:39
 */
class WechatTest {
    private MessageServiceInjector injector;

    @BeforeEach
    void setUp() {
        injector = () -> new Wechat((message, receiver) -> System.out.println("模拟信息服务"));
    }

    @AfterEach
    void tearDown() {
        injector = null;
    }

    @Test
    void processMessage() {
        var app = injector.getConsumer();
        app.processMessage("hello", "123");
    }
}