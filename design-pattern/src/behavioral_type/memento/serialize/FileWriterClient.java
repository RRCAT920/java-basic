package behavioral_type.memento.serialize;

/**
 * @author huzihao
 * @since 2020/11/5 08:37
 */
public class FileWriterClient {
    public static void main(String[] args) {
        var fileWriterCaretaker = new FileWriterCaretaker();
        var fileWriterUtil = new FileWriterUtil("data.txt");

        fileWriterUtil.write("first\n");
        fileWriterCaretaker.save(fileWriterUtil);
        fileWriterUtil.write("second\n");

        System.out.println("Before: ");
        System.out.println(fileWriterUtil);

        System.out.println("Restored: ");
        fileWriterCaretaker.undo(fileWriterUtil);
        System.out.println(fileWriterUtil);
    }
}
