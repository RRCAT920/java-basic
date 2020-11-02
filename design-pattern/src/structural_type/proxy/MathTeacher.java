package structural_type.proxy;

/**
 * @author huzihao
 * @since 2020/11/2 23:29
 */
public class MathTeacher implements Teacher {
    @Override
    public void teach() {
        System.out.println("教数学");
    }
}
