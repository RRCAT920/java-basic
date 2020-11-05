package create_type.builder.computer_builder;

/**
 * @author huzihao
 * @since 2020/11/5 21:26
 */
public class Computer {
    // required parameters
    private final String ram;
    private final String hhd;
    // optional parameters
    private final boolean isGraphicsCardEnabled;
    private final boolean isBluetoothEnabled;

    public String getRam() {
        return ram;
    }

    public String getHhd() {
        return hhd;
    }

    public boolean isGraphicsCardEnabled() {
        return isGraphicsCardEnabled;
    }

    public boolean isBluetoothEnabled() {
        return isBluetoothEnabled;
    }

    private Computer(ComputerBuilder computerBuilder) {
        ram = computerBuilder.ram;
        hhd = computerBuilder.hhd;
        isGraphicsCardEnabled = computerBuilder.isGraphicsCardEnabled;
        isBluetoothEnabled = computerBuilder.isBluetoothEnabled;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "ram='" + ram + '\'' +
                ", hhd='" + hhd + '\'' +
                ", isGraphicsCardEnabled=" + isGraphicsCardEnabled +
                ", isBluetoothEnabled=" + isBluetoothEnabled +
                '}';
    }

    public static class ComputerBuilder {
        // required parameters
        private final String ram;
        private final String hhd;
        // optional parameters
        private boolean isGraphicsCardEnabled;
        private boolean isBluetoothEnabled;

        public ComputerBuilder(String ram, String hhd) {
            this.ram = ram;
            this.hhd = hhd;
        }

        public ComputerBuilder setGraphicsCardEnabled(boolean graphicsCardEnabled) {
            isGraphicsCardEnabled = graphicsCardEnabled;
            return this;
        }

        public ComputerBuilder setBluetoothEnabled(boolean bluetoothEnabled) {
            isBluetoothEnabled = bluetoothEnabled;
            return this;
        }

        public Computer toComputer() {
            return new Computer(this);
        }
    }
}
