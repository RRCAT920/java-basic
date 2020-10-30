package singleton.enum_class;

/**
 * @author huzihao
 * @since 2020/10/30 16:53
 */
public enum Singleton {
    INSTANCE;

    @SuppressWarnings({"ConstantConditions", "UnnecessaryLocalVariable"})
    public static void main(String[] args) {
        var s1 = Singleton.INSTANCE;
        var s2 = Singleton.INSTANCE;
        assert s1 == s2;
    }
}
