package behavioral_type.memento.serialize;

import java.io.Serializable;

/**
 * @author huzihao
 * @since 2020/11/5 08:26
 */
public class FileWriterUtil implements Serializable {
    private static final long serialVersionUID = 1385065445996388324L;
    private String fileName;
    private StringBuilder content;

    public FileWriterUtil(String fileName) {
        this.fileName = fileName;
        content = new StringBuilder();
    }

    @Override
    public String toString() {
        return content.toString();
    }

    public void write(String str) {
        content.append(str);
    }

    public void undoToLastSave(Object obj) {
        if (obj instanceof FileWriterUtil fileWriterUtil) {
            fileName = fileWriterUtil.fileName;
            content = fileWriterUtil.content;
        }
    }
}
