package module;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author huzihao
 * @since 2020/9/14 23:38
 */
public class TryWithResourcesTest {

    @Test
    public void tryUpgrade() {
        var rd = new InputStreamReader(System.in);
        try (rd) {
            var read = rd.read();
            // 进行操作
            System.out.println(read);
//            rd = null;❎
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
