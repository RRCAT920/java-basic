package behavioral_type.chain_of_reponsibility.atm_dispenser;

/**
 * @author huzihao
 * @since 2020/11/9 16:49
 */
public class AtmDispenseChain {
    private DispenseChain chain;

    public AtmDispenseChain() {
        chain = new Yuan50Dispenser();
        var next = new Yuan20Dispenser();
        var last = new Yuan10Dispenser();
        chain.setNextChain(next);
        next.setNextChain(last);
    }

    public DispenseChain getChain() {
        return chain;
    }
}
