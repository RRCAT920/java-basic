package create_type.prototype.deep_clone;


import org.junit.Test;

/**
 * @author huzihao
 * @since 2020/10/31 22:33
 */
public class Client {
    @Test
    public void cloneImpl() throws CloneNotSupportedException {
        var deep = new DeepPrototype();
        deep.name = "张三";
        deep.target = new DeepCloneableTarget("李四", "引用类型");
        var deep2 = (DeepPrototype) deep.clone();

        assert deep.target != deep2.target;
    }

    @Test
    public void serializeAndDeserialize() {
        var deep = new DeepPrototype();
        deep.name = "张三";
        deep.target = new DeepCloneableTarget("李四", "引用类型");

        var deep2 = (DeepPrototype) deep.deepClone();

        assert deep.target != deep2.target;
    }
}
