package reflection;

/**
 * @author huzihao
 * @since 2020/9/13 02:13
 */
public class SuperMan implements Human {
    @Override
    public String getBelief() {
        return "I believe I can fly!";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}
