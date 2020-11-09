package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:00
 */
public interface FileSystem {
    void openFile();

    void writeFile();

    void closeFile();
}
