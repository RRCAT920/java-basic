package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:06
 */
public class FileSystemFactory {
    public static FileSystem getFileSystem() {
        var osName = System.getProperty("os.name");
        if (osName.contains("Windows")) return new WindowsFileSystem();
        else return new UnixFileSystem();
    }
}
