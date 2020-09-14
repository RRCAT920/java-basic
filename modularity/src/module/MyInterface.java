package module;

/**
 * @author huzihao
 * @since 2020/9/14 23:25
 */
public interface MyInterface {
    private void privateMethod1() {
        System.out.println("私有方法1");
    }

    private void privateMethod2() {
        System.out.println("私有方法2");
    }

    default void run() {
        privateMethod1();
        accept();
        privateMethod2();
    }

    void accept();

    static void main(String[] args) {
        var runnable = (MyInterface) () -> System.out.println("中间逻辑处理");
        runnable.run();
    }
}
