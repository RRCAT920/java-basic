package prototype;

import java.io.IOException;

/**
 * 普通方式克隆对象
 * <ol>
 *     <li>需要调用getter可能消耗大量资源</li>
 *     <li>不能动态的根据源码的变化来克隆对象</li>
 * </ol>
 */
public class Client {
    public static void main(String[] args) {
        Sheep sheep = new Sheep("tom", 13, "white", "123213");
        sheep.setFriend(new Sheep("kitty", 12, "black", "321321"));

        try {
            Sheep sheep1 = null;
            try {
                sheep1 = (Sheep) sheep.deepClone();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            var sheep2 = (Sheep) sheep.clone();
            var sheep3 = (Sheep) sheep.clone();
            var sheep4 = (Sheep) sheep.clone();

            System.out.println((sheep1 != null ? sheep1.getFriend() : null) == sheep.getFriend());
            System.out.println(sheep2.getFriend() == sheep.getFriend());
            System.out.println(sheep3);
            System.out.println(sheep4);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
