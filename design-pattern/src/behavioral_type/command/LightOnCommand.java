package behavioral_type.command;

/**
 * @author huzihao
 * @since 2020/11/3 13:03
 */
public class LightOnCommand implements Command {
    LightReceiver lightReceiver;

    public LightOnCommand(LightReceiver lightReceiver) {
        this.lightReceiver = lightReceiver;
    }

    @Override
    public void execute() {
        lightReceiver.on();
    }

    @Override
    public void undo() {
        lightReceiver.off();
    }
}
