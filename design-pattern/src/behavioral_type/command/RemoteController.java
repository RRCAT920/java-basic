package behavioral_type.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/3 13:07
 */
public class RemoteController {
    private final List<Command> onCommandList = new ArrayList<>(5);
    private final List<Command> offCommandList = new ArrayList<>(5);
    private Command commandToUndo;

    public RemoteController() {
        Collections.fill(onCommandList, NoneCommand.NONE_COMMAND);
        Collections.fill(offCommandList, NoneCommand.NONE_COMMAND);
        commandToUndo = NoneCommand.NONE_COMMAND;
    }

    public void addCommand(Command on, Command off) {
        onCommandList.add(on);
        offCommandList.add(off);
    }

    public void addCommand(int index, Command on, Command off) {
        onCommandList.add(index, on);
        offCommandList.add(index, off);
    }

    public void turnOn(int index) {
        execute(onCommandList.get(index));
    }

    public void turnOff(int index) {
        execute(offCommandList.get(index));
    }

    private void execute(Command cmd) {
        cmd.execute();
        commandToUndo = cmd;
    }

    /**
     * 未实现二次undo
     */
    public void undo() {
        commandToUndo.undo();
    }
}
