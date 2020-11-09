package behavioral_type.chain_of_reponsibility.atm_dispenser;

/**
 * @author huzihao
 * @since 2020/11/9 16:41
 */
public class Currency {
    private final int amount;

    public int getAmount() {
        return amount;
    }

    public Currency(int amount) {
        this.amount = amount;
    }
}
