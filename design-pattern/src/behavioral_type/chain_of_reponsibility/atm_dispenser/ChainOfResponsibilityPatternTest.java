package behavioral_type.chain_of_reponsibility.atm_dispenser;

import java.util.Scanner;

/**
 * @author huzihao
 * @since 2020/11/9 16:53
 */
public class ChainOfResponsibilityPatternTest {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        var atmDispenseChain = new AtmDispenseChain();
        while (in.hasNext()) {
            var amt = in.nextInt();
            if (amt % 10 != 0) {
                System.out.println("需要是10的倍数");
                break;
            }
            atmDispenseChain.getChain().dispense(new Currency(amt));
        }
    }
}
