package behavioral_type.visitor;

/**
 * @author huzihao
 * @since 2020/11/3 16:56
 */
public class Fail extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男人评价差");
    }

    @Override
    public void getWomenResult(Woman woman) {
        System.out.println("女人评价差");
    }
}
