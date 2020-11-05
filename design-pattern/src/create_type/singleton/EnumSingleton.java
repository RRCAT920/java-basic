package create_type.singleton;

/**
 * @author huzihao
 * @since 2020/11/5 16:23
 */
public enum EnumSingleton {
    INSTANCE;

    EnumSingleton() {
        System.out.println("not lazy");
    }

    public static void doSomething() {
        System.out.println("....");
    }

//    protected Object readResolve() {
//        return INSTANCE;
//    }
}
