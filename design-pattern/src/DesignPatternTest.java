import org.junit.Test;

import singleton.static_final_hunger.Singleton;

/**
 * @author huzihao
 * @since 2020/8/26 23:45
 */
public class DesignPatternTest {
    @Test
    public void testSingleton() {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}
