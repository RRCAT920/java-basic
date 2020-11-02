package facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:38
 */
public enum DVDPlayer {
    DVD_PLAYER;

    public void on() {
        System.out.println("DVD on");
    }

    public void off() {
        System.out.println("DVD off");
    }

    public void play() {
        System.out.println("DVD play");
    }

    public void pause() {
        System.out.println("DVD pause");
    }
}
