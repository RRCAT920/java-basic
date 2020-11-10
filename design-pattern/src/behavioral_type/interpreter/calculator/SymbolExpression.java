package behavioral_type.interpreter.calculator;

import java.util.Map;

/**
 * @author huzihao
 * @since 2020/11/10 11:37
 */
public abstract class SymbolExpression extends Expression {
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    // 默认实现，延迟到子类实现
    @Override
    public int interpret(Map<String, Integer> strIntMap) {
        return 0;
    }
}
