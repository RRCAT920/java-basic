package reflection.unique;

import java.io.Serializable;

/**
 * @author huzihao
 * @since 2020/9/12 12:59
 */
public class Creature<T> implements Serializable {
//    public static final long serialVersionUID = 4212321321L;
    private char gender;
    public double weight;


    private void breath() {
        System.out.println("生物呼吸");
    }

    public void eat() {
        System.out.println("生物吃东西");
    }
}
