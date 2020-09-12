package reflection;

import java.io.File;
import java.io.IOException;

/**
 * @author huzihao
 * @since 2020/9/13 00:52
 */
public class Mytxt {
    public void myCreate() throws IOException {
        var file = new File("myhello.txt");
        file.createNewFile();
    }
}
