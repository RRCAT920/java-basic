package behavioral_type.memento;

/**
 * @author huzihao
 * @since 2020/11/5 08:26
 */
public class FileWriterUtil {
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

    public Memento save() {
        return new Memento(fileName, content);
    }

    public void undoToLastSave(Object obj) {
        if (obj instanceof Memento memento) {
            fileName = memento.fileName;
            content = memento.content;
        }
    }

    private static class Memento {
        private final String fileName;
        private final StringBuilder content;

        public Memento(String fileName, StringBuilder content) {
            this.fileName = fileName;
            // 深拷贝
            this.content = new StringBuilder(content);
        }
    }
}
