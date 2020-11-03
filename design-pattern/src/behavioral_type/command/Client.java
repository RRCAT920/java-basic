package behavioral_type.command;

/**
 * @author huzihao
 * @since 2020/11/3 14:22
 */
public class Client {
    public static void main(String[] args) {
        var remoteController = new RemoteController();
        var lightReceiver = new LightReceiver();
        var on = new LightOnCommand(lightReceiver);
        var off = new LightOffCommand(lightReceiver);

        remoteController.addCommand(on, off);

        remoteController.turnOn(0);
        remoteController.turnOff(0);
        remoteController.undo();
    }
}
