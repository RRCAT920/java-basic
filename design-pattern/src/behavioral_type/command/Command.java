package behavioral_type.command;

/**
 * @author huzihao
 * @since 2020/11/3 13:02
 */
public interface Command {
    void execute();

    void undo();
}
