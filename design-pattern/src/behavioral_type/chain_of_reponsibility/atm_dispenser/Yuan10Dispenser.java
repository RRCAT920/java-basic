package behavioral_type.chain_of_reponsibility.atm_dispenser;

/**
 * @author huzihao
 * @since 2020/11/9 16:48
 */
public class Yuan10Dispenser implements DispenseChain {
    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        var amt = currency.getAmount();
        if (amt >= 10) {
            int num = amt / 10;
            System.out.println("分发" + num + "张10元");

            int remainder = amt % 10;
            if (0 != remainder) nextChain.dispense(new Currency(remainder));
        } else nextChain.dispense(currency);
    }
}
