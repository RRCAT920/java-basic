package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:04
 */
public class CloseCommand implements Command {
    private FileSystem fileSystem;

    public CloseCommand(FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    @Override
    public void execute() {
        fileSystem.closeFile();
    }
}
