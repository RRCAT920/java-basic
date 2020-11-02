package create_type.prototype.deep_clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author huzihao
 * @since 2020/10/31 22:24
 */
public class DeepPrototype implements Serializable, Cloneable {
    public String name;
    public DeepCloneableTarget target;

    /**
     * 重写clone方式实现深拷贝
     *
     * @return 拷贝的对象
     * @throws CloneNotSupportedException 不支持克隆的异常
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        var deepPrototype = (DeepPrototype) super.clone();
        deepPrototype.target = (DeepCloneableTarget) target.clone();
        return deepPrototype;
    }

    public Object deepClone() {
        try (var byteArrayOut = new ByteArrayOutputStream();
             var objectOut = new ObjectOutputStream(byteArrayOut)) {
            objectOut.writeObject(this);

            try (var byteArrayIn = new ByteArrayInputStream(byteArrayOut.toByteArray());
                 var objectIn = new ObjectInputStream(byteArrayIn)) {
                return objectIn.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
