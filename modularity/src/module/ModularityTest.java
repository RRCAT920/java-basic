package module;// Junit5不能运行无名包的程序

import org.junit.jupiter.api.Test;

/**
 * @author huzihao
 * @since 2020/9/14 22:48
 */
public class ModularityTest {
    @Test
    public void moduleTest() {
        System.out.println("这是JDK9的模块系统");
    }
}
