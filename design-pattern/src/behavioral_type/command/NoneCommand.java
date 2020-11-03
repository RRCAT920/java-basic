package behavioral_type.command;

/**
 * @author huzihao
 * @since 2020/11/3 14:09
 */
public enum NoneCommand implements Command {
    NONE_COMMAND;

    @Override
    public void execute() {

    }

    @Override
    public void undo() {

    }
}
