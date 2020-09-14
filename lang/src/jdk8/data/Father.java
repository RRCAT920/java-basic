package jdk8.data;

/**
 * @author huzihao
 * @since 2020/9/14 12:12
 */
public class Father {
    private String name;
    private Son son;

    public Father() {}

    public Father(String name) {
        this.name = name;
    }

    public Father(String name, Son son) {
        this.name = name;
        this.son = son;
    }

    public String getName() {
        return name;
    }

    public Son getSon() {
        return son;
    }
}
