package structural_type.proxy.static_proxy;

/**
 * @author huzihao
 * @since 2020/11/2 23:32
 */
public class Client {
    public static void main(String[] args) {
        Teacher teacher = new MathTeacherProxy(new MathTeacher());

        teacher.teach();
    }
}
