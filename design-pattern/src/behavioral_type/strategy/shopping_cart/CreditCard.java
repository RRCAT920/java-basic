package behavioral_type.strategy.shopping_cart;

/**
 * @author huzihao
 * @since 2020/11/9 20:26
 */
public class CreditCard implements Payments {
    private String cardNumber;
    private String password;

    public CreditCard(String cardNumber, String password) {
        this.cardNumber = cardNumber;
        this.password = password;
    }

    @Override
    public void pay(int amount) {
        System.out.println("用银行卡支付" + amount + "元");
    }
}
