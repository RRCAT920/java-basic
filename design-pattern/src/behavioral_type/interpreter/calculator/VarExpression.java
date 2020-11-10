package behavioral_type.interpreter.calculator;

import java.util.Map;

/**
 * @author huzihao
 * @since 2020/11/10 11:35
 */
public class VarExpression extends Expression {
    private String key;

    public VarExpression(String key) {
        this.key = key;
    }

    @Override
    public int interpret(Map<String, Integer> strIntMap) {
        return strIntMap.get(key);
    }
}
