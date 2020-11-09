package behavioral_type.chain_of_reponsibility.atm_dispenser;

/**
 * @author huzihao
 * @since 2020/11/9 16:43
 */
public class Yuan50Dispenser implements DispenseChain {
    private DispenseChain nextChain;

    @Override
    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    @Override
    public void dispense(Currency currency) {
        var amt = currency.getAmount();
        if (amt >= 50) {
            int num = amt / 50;
            System.out.println("分发" + num + "张50元");
            
            int remainder = amt % 50;
            if (0 != remainder) nextChain.dispense(new Currency(remainder));
        } else nextChain.dispense(currency);
    }
}
