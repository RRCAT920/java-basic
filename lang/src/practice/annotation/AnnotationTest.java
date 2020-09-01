package practice.annotation;

import org.junit.Test;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * @author huzihao
 * @since 2020/9/2 00:04
 */
public class AnnotationTest {
    @Test
    public void testPerson() {
        var person = new Person("李容蓉");
        assert "👋我叫李容蓉。".equals(person.toString());
    }

    /**
     * 我没有指定父类，也没有实现接口，还能不能写匿名内部类。
     */
    @Test
    public void riddle() {
        new Object(){
            void fun(){}
        }.fun();
    }

    @Test
    public void testStudent() {
        try {
            var annotations = Student.class.getMethod("toString").getAnnotations();
            assert "java.lang.Deprecated".equals(annotations[0].annotationType().getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTeacher() {
        Method say = null;
        try {
            say = Teacher.class.getMethod("say");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (say != null) {
            var annotations = say.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }
        }
    }
}

/**
 * 1）.编写一个Person类，使用Override注解它的toString方法
 * 2）.自定义一个名为“MyTiger”的注解类型，它只可以使用在方法上，带一个String类型的value属性，然后在第1题中的Person类上正确使用。
 */
class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    @MyTiger("My tiger annotates this method")
    @Override
    public String toString() {
        return "👋我叫" + name + "。";
    }
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@interface MyTiger {
    String value();
}

/**
 * 对成员内部类的继承说明
 */
class Test1 extends WithInner.Inner {
    Test1(WithInner w){
        w.super();
    }
}
class WithInner{
    class Inner{

    }
}

/**
 * 自定义annotation，里面包括1个String类型的属性，一个有默认值类型的属性
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
@interface MyAnnotation {
    String firstValue();

    String secondValue() default "2";
}

/**
 * 定义一个Student类，要求重写toString()的方法，并且此方法要使用Annotation的三个基本的注解，创建Test类，
 * 输出Student类的toString方法的所有注解
 */
class Student {
    private final String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    @Deprecated
    @SuppressWarnings({})
    public String toString() {
        return name;
    }
}

/**
 * 自定义一个Annotation，定义Teacher类，定义say()方法，使用Annotation的三个基本的注释和自定义的Annotation，打印出
 * Teacher类的say()的自定义的注释，并输出注释的属性值
 */
class Teacher implements Works {
    @Override
    @Deprecated
    @SuppressWarnings({})
    @MyTiger("tiger")
    public void say() {

    }
}

interface Works {
    void say();
}
