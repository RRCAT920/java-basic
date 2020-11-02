package adapter.interface_adapter;

/**
 * @author huzihao
 * @since 2020/11/2 16:57
 */
public class Client {
    public static void main(String[] args) {
        var opr1Adapter = new AbstractAdapter() {
            @Override
            public void opr1() {
                System.out.println("仅实现opr1方法");
            }
        };
        opr1Adapter.opr1();
    }
}
