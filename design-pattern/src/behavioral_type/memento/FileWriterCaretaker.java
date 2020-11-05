package behavioral_type.memento;

/**
 * @author huzihao
 * @since 2020/11/5 08:35
 */
public class FileWriterCaretaker {
    private Object object;

    public void save(FileWriterUtil fileWriterUtil) {
        object = fileWriterUtil.save();
    }

    public void undo(FileWriterUtil fileWriterUtil) {
        fileWriterUtil.undoToLastSave(object);
    }
}
