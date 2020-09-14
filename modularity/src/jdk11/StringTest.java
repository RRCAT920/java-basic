package jdk11;

import org.junit.jupiter.api.Test;

/**
 * @author huzihao
 * @since 2020/9/15 02:09
 */
public class StringTest {
    @Test
    public void strMethod() {
        assert "   ".isBlank();
        assert "Java".equals("   Java   ".strip());
        assert "Java   ".equals("   Java   ".stripLeading());
        assert "   Java".equals("   Java   ".stripTrailing());
        assert "AAA".equals("A".repeat(3));
        assert 4 == "hello\nfrom\nout\nside".lines().count();
    }
}
