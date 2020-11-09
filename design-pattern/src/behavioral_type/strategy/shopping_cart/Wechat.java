package behavioral_type.strategy.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 20:27
 */
public class Wechat implements Payments {
    private String phoneNumber;
    private String password;

    public Wechat(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println("用微信支付" + amount + "元");
    }
}
