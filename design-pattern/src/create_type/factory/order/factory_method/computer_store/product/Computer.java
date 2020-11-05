package create_type.factory.order.factory_method.computer_store.product;

/**
 * @author huzihao
 * @since 2020/11/5 18:05
 */
public abstract class Computer {
    protected String ram;
    protected String hhd;
    protected String cpu;
    protected String os;
    protected String osVersion;

    public Computer(String ram, String hhd, String cpu, String os, String osVersion) {
        this.ram = ram;
        this.hhd = hhd;
        this.cpu = cpu;
        this.os = os;
        this.osVersion = osVersion;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "{" +
                "ram='" + ram + '\'' +
                ", hhd='" + hhd + '\'' +
                ", cpu='" + cpu + '\'' +
                ", os='" + os + '\'' +
                ", osVersion='" + osVersion + '\'' +
                '}';
    }

    public String getRam() {
        return ram;
    }

    public String getHhd() {
        return hhd;
    }

    public String getCpu() {
        return cpu;
    }

    public String getOs() {
        return os;
    }

    public String getOsVersion() {
        return osVersion;
    }
}
