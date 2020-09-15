package jdk11;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author huzihao
 * @since 2020/9/15 13:26
 */
public class OptionalTest {
    @SuppressWarnings({"ConstantConditions", "SimplifyOptionalCallChains"})
    @Test
    public void empty() {
        var optNumber = Optional.empty();
        assert !optNumber.isPresent();
        assert optNumber.isEmpty();
    }
}
