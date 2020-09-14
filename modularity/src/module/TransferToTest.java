package module;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author huzihao
 * @since 2020/9/15 00:15
 */
public class TransferToTest {
    @Test
    public void switchTo() {
        try (var fileIn = new FileInputStream("in.txt");
             var fileOut = new FileOutputStream("out.txt")) {
            fileIn.transferTo(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
