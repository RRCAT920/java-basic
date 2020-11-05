package create_type.builder.builder.computer_builder;

/**
 * @author huzihao
 * @since 2020/11/5 21:30
 */
public class BuilderPatternTest {
    public static void main(String[] args) {
        var computer = new Computer.ComputerBuilder("64GB", "1TB")
                .setGraphicsCardEnabled(true)
                .setBluetoothEnabled(true)
                .toComputer();
        System.out.println(computer);
    }
}
