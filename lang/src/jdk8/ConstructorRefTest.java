package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import jdk8.data.Employee;

/**
 * 一、构造器引用
 *
 * 二、数组引用
 *
 *
 * Created by shkstart
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()
    @Test
    public void test1(){
        var ctor = (Supplier<Employee>) Employee::new;
        System.out.println(ctor.get());
    }

	//Function中的R apply(T t)
    @Test
    public void test2(){
        var ctor = (Function<Integer, Employee>) Employee::new;
        assert 1000 == ctor.apply(1000).getId();
    }

	//BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        var ctor = (BiFunction<Integer, String, Employee>) Employee::new;
        var employee = ctor.apply(1000, "Rongrong");
        assert 1000 == employee.getId();
        assert "Rongrong".equals(employee.getName());
	}

	//数组引用
    //Function中的R apply(T t)
    @Test
    public void test4(){
        var ctor = (Function<Integer, String[]>) String[]::new;
        var strings = ctor.apply(5);
        strings[0] = "hello";
        strings[1] = "world";
        strings[2] = "hey";
        strings[3] = "jude";
        strings[4] = "it's me";

        for (var str : strings) {
            System.out.println(str);
        }
    }

    @Test
    public void test5() {
        var listCtor = (Function<Integer, List<String>>) ArrayList::new;
        var strings = listCtor.apply(10);
        strings.addAll(Arrays.asList("hello", "world", "hey", "jude"));
        for (var str : strings) {
            System.out.println(str);
        }
    }
}
