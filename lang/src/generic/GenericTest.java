package generic;

import org.junit.Test;

import java.util.ArrayList;

/**
 * @author huzihao
 * @since 2020/9/5 12:14
 */
public class GenericTest {
    /**
     * 泛型：定义类、接口时通过一个标识表示属性的类型、返回值的类型，参数的类型，这个类型参数在使用时确定✅。
     * （默认类型参数是java.lang.Object，类型参数必须是类，不能是基本数据类型）
     */
    @Test
    public void genericOverview() {
        var arrayList = new ArrayList();
        boolean hasClassCastException = false;

        // 类型不安全🔐
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add("3");

        for (Object o : arrayList) {
            try {
                // 导致强转不安全🔐
                int value = (Integer) o;
            } catch (ClassCastException e) {
                hasClassCastException = true;
            }
        }

        assert hasClassCastException;

        // 解决类型安全问题，从而解决强转安全问题
        var numbers = new ArrayList<Integer>();

        numbers.add(1);
        numbers.add(2);
//        Required type Integer
//        Provided String
//        numbers.add("3");
        numbers.add(3);

        for (var number : numbers) {

        }
    }
}
