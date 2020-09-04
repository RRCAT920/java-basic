package practice.collection.list;

import org.junit.Test;

import java.util.*;

/**
 * @author huzihao
 * @since 2020/9/4 22:39
 */
public class ListTest {
    /**
     * 请从键盘随机输入10个整数保存到List中，并按倒序、从大 到小的顺序显示出来
     */
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        List list = new ArrayList();

        for (int i = 0; i < 10; i++) {
            list.add(in.nextInt());
        }

        Collections.reverse(list);
        System.out.println(list);

        Collections.sort(list, ((o1, o2) -> -Integer.compare((Integer) o1, (Integer) o2)));
        System.out.println(list);
    }

    /**
     * 请把学生名与考试分数录入到集合中，并按分数显示前三名 成绩学员的名字。
     */
    @Test
    public void Top3() {
        class Student implements Comparable {
            String name;
            int score;
            int id;

            public Student(String name, int score, int id) {
                this.name = name;
                this.score = score;
                this.id = id;
            }

            @Override
            public int compareTo(Object o) {
                if (o instanceof Student) return this.score - ((Student) o).score;
                throw new RuntimeException("o的类型不是Student");
            }
        }

        var scoreDescendingCOMP = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Student && o2 instanceof Student) {
                    return -((Student) o1).compareTo(o2);
                }
                throw new RuntimeException("类型不匹配");
            }
        };

        var treeSet = new TreeSet(scoreDescendingCOMP);
        treeSet.add(new Student("张三", 99, 1));
        treeSet.add(new Student("李四", 82, 2));
        treeSet.add(new Student("王五", 58, 3));
        treeSet.add(new Student("刘六", 98, 4));
        treeSet.add(new Student("何七", 85, 5));

        var itor = treeSet.iterator();
        for (int i = 0; i < 3; i++) {
            System.out.println();
        }
    }
}
