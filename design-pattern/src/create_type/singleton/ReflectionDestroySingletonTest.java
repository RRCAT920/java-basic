package create_type.singleton;

import java.lang.reflect.InvocationTargetException;

/**
 * @author huzihao
 * @since 2020/11/5 16:15
 */
public class ReflectionDestroySingletonTest {
    public static void main(String[] args) {
        try {
            var ctor = BillPughSingleton.class.getDeclaredConstructor();
            ctor.setAccessible(true);
            var inst2 = ctor.newInstance();
            var inst1 = BillPughSingleton.getInstance();

            assert inst1 != inst2;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
