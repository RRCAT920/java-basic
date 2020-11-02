package facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:42
 */
public enum Stereo {
    STEREO;

    public void on() {
        System.out.println("Stereo on");
    }

    public void off() {
        System.out.println("Stereo off");
    }

    public void turnUp() {
        System.out.println("Stereo up");
    }
}
