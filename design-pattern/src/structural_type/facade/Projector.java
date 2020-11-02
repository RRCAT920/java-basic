package structural_type.facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:41
 */
public enum Projector {
    PROJECTOR;

    public void on() {
        System.out.println("Projector on");
    }

    public void off() {
        System.out.println("Projector off");
    }

    public void project() {
        System.out.println("Projector project");
    }
}
