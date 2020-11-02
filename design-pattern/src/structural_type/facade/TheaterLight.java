package structural_type.facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:43
 */
public enum TheaterLight {
    THEATER_LIGHT;

    public void on() {
        System.out.println("TheaterLight on");
    }

    public void off() {
        System.out.println("TheaterLight off");
    }

    public void dim() {
        System.out.println("TheaterLight dim");
    }

    public void bright() {
        System.out.println("TheaterLight bright");
    }
}
