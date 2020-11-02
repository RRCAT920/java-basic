package structural_type.facade;

/**
 * @author huzihao
 * @since 2020/11/2 20:51
 */
public class Client {
    public static void main(String[] args) {
        var homeTheater = new HomeTheaterFacade();
        homeTheater.ready();
        System.out.println("-------------");
        homeTheater.play();
        System.out.println("-------------");
        homeTheater.pause();
        System.out.println("-------------");
        homeTheater.play();
        System.out.println("-------------");
        homeTheater.end();
    }
}
