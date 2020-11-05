package create_type.singleton;

import java.io.Serializable;

/**
 * @author huzihao
 * @since 2020/11/5 11:14
 */
public class BillPughSingleton implements Serializable {
    private static final long serialVersionUID = -5121033406062597759L;

    private BillPughSingleton() {
    }

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }

    protected Object readResolve() {
        return getInstance();
    }
}
