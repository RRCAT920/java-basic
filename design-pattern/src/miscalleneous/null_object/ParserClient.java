package miscalleneous.null_object;

import java.util.Scanner;

/**
 * @author huzihao
 * @since 2020/11/5 11:57
 */
public class ParserClient {
    public static void main(String[] args) {
        var in = new Scanner(System.in);
        while (true) {
            var input = in.next();
            ParserImpl.PARSER.findAction(input).doSomething();
        }
    }
}
