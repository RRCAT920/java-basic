package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:05
 */
public class FileInvoker {
    private Command cmd;

    public FileInvoker() {
    }

    public void setCmd(Command cmd) {
        this.cmd = cmd;
    }

    public void execute() {
        cmd.execute();
    }
}
