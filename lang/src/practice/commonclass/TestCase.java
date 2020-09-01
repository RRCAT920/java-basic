package practice.commonclass;

import java.util.ArrayList;

/**
 * @author huzihao
 * @since 2020/8/31 16:56
 */
public class TestCase {
    public static class KeyValue {
        private String key;
        private String value;

        private KeyValue(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public String getValue() {
            return value;
        }
    }

    public static final ArrayList<KeyValue> cases = new ArrayList<>(10);

    public static void add(String key, String value) {
        cases.add(new KeyValue(key, value));
    }
}
