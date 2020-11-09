package behavioral_type.chain_of_reponsibility.atm_dispenser;

/**
 * @author huzihao
 * @since 2020/11/9 16:42
 */
public interface DispenseChain {
    void setNextChain(DispenseChain nextChain);

    void dispense(Currency currency);
}
