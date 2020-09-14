package jdk8;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import jdk8.data.Employee;

/**
 * 练习提供
 * 方法引用的使用
 *
 * Created by shkstart.
 */
public class MethodRefTest {

	// 情况一：对象 :: 实例方法
	//Consumer中的void accept(T t)
	//PrintStream中的void println(T t)
	@Test
	public void test1() {
		final var consumer = (Consumer<String>) System.out::println;
		consumer.accept("对象::实例方法");
	}
	
	//Supplier中的T get()
	//Employee中的String getName()
	@Test
	public void test2() {
		var employee = new Employee(1, "Rongrong");
		var nameSupplier = (Supplier<String>) employee::getName;
		System.out.println(nameSupplier.get());
	}

	// 情况二：类 :: 静态方法
	//Comparator中的int compare(T t1,T t2)
	//Integer中的int compare(T t1,T t2)
	@Test
	public void test3() {
		var intComp = (Comparator<Integer>) Integer::compare;
		System.out.println(intComp.compare(1, 2));
	}
	
	//Function中的R apply(T t)
	//Math中的Long round(Double d)
	@Test
	public void test4() {
		var fun = (Function<Double, Long>) Math::round;
		System.out.println(fun.apply(12.6));
	}

	// 情况三：类 :: 实例方法 
	// Comparator中的int comapre(T t1,T t2)
	// String中的int t1.compareTo(t2)
	@Test
	public void test5() {
		var strComp = (Comparator<String>) String::compareTo;
		var executed = false;
		try {
			strComp.compare(null, "hello");
		} catch (Exception e) {
			executed = true;
		}
		assert executed;

		assert strComp.compare("hello", "null") < 0;
	}

	//BiPredicate中的boolean test(T t1, T t2);
	//String中的boolean t1.equals(t2)
	@Test
	public void test6() {
		var strEquals = (BiPredicate<String, String>) String::equals;
		assert strEquals.test("hello", new String("hello"));
	}
	
	// Function中的R apply(T t)
	// Employee中的String getName();
	@Test
	public void test7() {
		var fun = (Function<Employee, String>) Employee::getName;
		var emp = new Employee(1, "调用者");
		System.out.println(fun.apply(emp));
	}

}
