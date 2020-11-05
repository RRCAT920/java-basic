package behavioral_type.memento.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author huzihao
 * @since 2020/11/5 08:35
 */
public class FileWriterCaretaker {
    private byte[] bytes;

    public void save(FileWriterUtil fileWriterUtil) {
        try (var byteArrayOut = new ByteArrayOutputStream();
             var objectOut = new ObjectOutputStream(byteArrayOut)) {
            objectOut.writeObject(fileWriterUtil);
            bytes = byteArrayOut.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void undo(FileWriterUtil fileWriterUtil) {
        try (var byteArrayIn = new ByteArrayInputStream(bytes);
             var objectIn = new ObjectInputStream(byteArrayIn)) {
            fileWriterUtil.undoToLastSave(objectIn.readObject());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
