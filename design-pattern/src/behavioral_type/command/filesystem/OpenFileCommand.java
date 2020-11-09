package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:03
 */
public class OpenFileCommand implements Command {
    private FileSystem fileSystem;

    public OpenFileCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        fileSystem.openFile();
    }
}
