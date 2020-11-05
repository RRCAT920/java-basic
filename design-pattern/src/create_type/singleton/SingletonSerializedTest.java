package create_type.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author huzihao
 * @since 2020/11/5 16:35
 */
public class SingletonSerializedTest {
    public static void main(String[] args) {
        var filename = "filename.ser";

        try (var fout = new FileOutputStream(filename);
             var objOut = new ObjectOutputStream(fout);
             var fin = new FileInputStream(filename);
             var objIn = new ObjectInputStream(fin)) {
            var inst1 = BillPughSingleton.getInstance();
            objOut.writeObject(inst1);

            var inst2 = (BillPughSingleton) objIn.readObject();

            System.out.println(inst1.hashCode());
            System.out.println(inst2.hashCode());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
