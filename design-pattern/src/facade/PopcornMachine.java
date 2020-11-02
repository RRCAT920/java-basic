package facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:39
 */
public enum PopcornMachine {
    POPCORN_MACHINE;

    public void on() {
        System.out.println("popcorn machine on");
    }

    public void off() {
        System.out.println("popcorn machine off");
    }

    public void pop() {
        System.out.println("popcorn machine pop");
    }
}
