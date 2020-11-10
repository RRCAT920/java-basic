package behavioral_type.interpreter.calculator;

import java.util.HashMap;
import java.util.Optional;
import java.util.Scanner;

/**
 * @author huzihao
 * @since 2020/11/10 11:54
 */
public class ClientTest {
    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        var exprStr = getExprStr();
        var strIntMap = getValue(exprStr);
        var calculator = new Calculator(exprStr);
        var result = calculator.run(strIntMap);

        System.out.println(exprStr + "=" + result);
    }

    private static HashMap<String, Integer> getValue(String exprStr) {
        var strIntHashMap = new HashMap<String, Integer>();
        for (var i = 0; i < Optional.of(exprStr).get().length(); i++) {
            var ch = exprStr.charAt(i);
            if (ch != '+' && ch != '-') {
                var key = String.valueOf(ch);
                System.out.print(key + "=");
                var value = in.nextInt();
                strIntHashMap.putIfAbsent(key, value);
            }
        }
        return strIntHashMap;
    }

    private static String getExprStr() {
        System.out.print("请输入表达式：");
        return in.nextLine();
    }
}
