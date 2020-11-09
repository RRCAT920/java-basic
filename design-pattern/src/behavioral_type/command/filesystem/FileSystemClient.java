package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:07
 */
public class FileSystemClient {
    public static void main(String[] args) {
        var fileSystem = FileSystemFactory.getFileSystem();
        var fileInvoker = new FileInvoker();

        fileInvoker.setCmd(new OpenFileCommand(fileSystem));
        fileInvoker.execute();

        fileInvoker.setCmd(new WriteCommand(fileSystem));
        fileInvoker.execute();

        fileInvoker.setCmd(new CloseCommand(fileSystem));
        fileInvoker.execute();
    }
}
