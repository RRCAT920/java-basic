package behavioral_type.interpreter.calculator;

import java.util.Map;

/**
 * @author huzihao
 * @since 2020/11/10 11:39
 */
public class SubExpression extends SymbolExpression {
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public int interpret(Map<String, Integer> strIntMap) {
        return left.interpret(strIntMap) - right.interpret(strIntMap);
    }
}
