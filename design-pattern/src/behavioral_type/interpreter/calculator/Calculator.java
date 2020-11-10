package behavioral_type.interpreter.calculator;

import java.util.Map;
import java.util.Optional;
import java.util.Stack;

/**
 * @author huzihao
 * @since 2020/11/10 11:47
 */
public class Calculator {
    private final Expression expression;

    public Calculator(String exprStr) {
        var exprStack = new Stack<Expression>();
        for (var i = 0; i < Optional.of(exprStr).get().length(); i++) {
            var ch = exprStr.charAt(i);
            Expression left;
            Expression right;
            switch (ch) {
                default -> exprStack.push(new VarExpression(String.valueOf(ch)));
                case '+' -> {
                    left = exprStack.pop();
                    right = new VarExpression(String.valueOf(exprStr.charAt(i + 1)));
                    i++;
                    exprStack.push(new AddExpression(left, right));
                }
                case '-' -> {
                    left = exprStack.pop();
                    right = new VarExpression(String.valueOf(exprStr.charAt(i + 1)));
                    i++;
                    exprStack.push(new SubExpression(left, right));
                }
            }
        }

        this.expression = exprStack.pop();
    }

    public int run(Map<String, Integer> strIntMap) {
        return expression.interpret(strIntMap);
    }
}
