package facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:46
 */
public class HomeTheaterFacade {
    private final TheaterLight theaterLight = TheaterLight.THEATER_LIGHT;
    private final PopcornMachine popcornMachine = PopcornMachine.POPCORN_MACHINE;
    private final Stereo stereo = Stereo.STEREO;
    private final Projector projector = Projector.PROJECTOR;
    private final Screen screen = Screen.SCREEN;
    private final DVDPlayer dvdPlayer = DVDPlayer.DVD_PLAYER;

    public void ready() {
        popcornMachine.on();
        popcornMachine.pop();

        screen.down();

        projector.on();

        stereo.on();

        dvdPlayer.on();

        theaterLight.dim();
    }

    public void play() {
        projector.project();
        dvdPlayer.play();
    }

    public void pause() {
        dvdPlayer.pause();
    }

    public void end() {
        popcornMachine.off();
        screen.up();
        projector.off();
        stereo.off();
        dvdPlayer.off();
        theaterLight.off();
    }
}
