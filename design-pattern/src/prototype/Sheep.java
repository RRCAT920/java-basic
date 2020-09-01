package prototype;

import java.io.*;

public class Sheep implements Cloneable, Serializable {
    private String name;
    private int age;
    private String color;
    private String id;
    private Sheep friend;

    public Sheep(String name, int age, String color, String id) {
        this.name = name;
        this.age = age;
        this.color = color;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Sheep getFriend() {
        return friend;
    }

    public void setFriend(Sheep friend) {
        this.friend = friend;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
//        Sheep sheep = (Sheep) super.clone();
//        if (friend != null) sheep.friend = (Sheep) friend.clone();
//        return sheep;
        return super.clone();
    }

    @Override
    public String toString() {
        return "Sheep{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    public Object deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);

            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            return ois.readObject();
        } finally {
            if (bos != null) bos.close();
            if (oos != null) oos.close();
            if (bis != null) bis.close();
            if (ois != null) ois.close();
        }
    }
}
