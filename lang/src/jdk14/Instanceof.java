package jdk14;

import java.util.Objects;

/**
 * @author huzihao
 * @since 2020/9/29 23:21
 */
public class Instanceof {
    private final String name;
    private final int id;

    public Instanceof(String name, int id) {
        this.name = name;
        this.id = id;
    }

    /**
     * 使用instanceof的模式匹配简化equals方法
     * @param o 一个对象
     * @return 两个对象属性对应相等时返回true，否则false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        return o instanceof Instanceof that && id == that.id && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
