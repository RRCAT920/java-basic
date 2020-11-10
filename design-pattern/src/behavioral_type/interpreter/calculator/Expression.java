package behavioral_type.interpreter.calculator;

import java.util.Map;

/**
 * @author huzihao
 * @since 2020/11/10 11:34
 */
public abstract class Expression {
    // a=10, b=20
    public abstract int interpret(Map<String, Integer> strIntMap);
}
