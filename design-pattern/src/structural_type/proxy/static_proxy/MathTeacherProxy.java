package structural_type.proxy.static_proxy;

/**
 * @author huzihao
 * @since 2020/11/2 23:29
 */
public class MathTeacherProxy implements Teacher {
    private final MathTeacher mathTeacher;

    public MathTeacherProxy(MathTeacher mathTeacher) {
        this.mathTeacher = mathTeacher;
    }

    @Override
    public void teach() {
        System.out.println("代课老师");
        mathTeacher.teach();
    }
}
