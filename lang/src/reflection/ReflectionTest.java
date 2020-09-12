package reflection;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.Random;

/**
 * @author huzihao
 * @since 2020/9/11 15:06
 */
public class ReflectionTest {
    /**
     * 反射之前的操作
     */
    @Test
    public void personBefore() {
        // 1.创建对象
        var p1 = new Person("Tom", 12);

        // 2.通过对象调用公开成员
        p1.age = 10;
        System.out.println(p1);
        p1.show();

        // 3.不能调用私有成员
    }

    /**
     * 反射之后的操作
     */
    @Test
    public void personAfter() throws IllegalAccessException, InvocationTargetException,
            InstantiationException, NoSuchMethodException, NoSuchFieldException {
        // 1.创建对象
        var personClass = Person.class;
        var personCtor = personClass.getConstructor(String.class, int.class);
        var person = (Person) personCtor.newInstance("Tom", 12);
        System.out.println(person);

        // 2.调用公开成员
        var age = personClass.getDeclaredField("age");
        age.set(person, 10);
        System.out.println(person);

        var show = personClass.getDeclaredMethod("show");
        show.invoke(person);

        // 3.调用私有成员
        // 私有构造器
        var personCtor2 =
                personClass.getDeclaredConstructor(String.class);
        personCtor2.setAccessible(true);
        var person2 = (Person) personCtor2.newInstance("Jerry");
        System.out.println(person2);

        // 私有属性
        var name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person2, "Rongrong");
        System.out.println(person2);

        // 私有方法
        var showNation = personClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        var nation = (String) showNation.invoke(person2, "中国");
        System.out.println(nation);
    }

    @SuppressWarnings("ConstantConditions") // personClass == personClass2
    @Test
    public void getObjOfClass() throws ClassNotFoundException {
        var personClass = Person.class;
        System.out.println(personClass);

        var person = new Person();
        var personClass2 = person.getClass();
        System.out.println(personClass2);

        var aClass = Class.forName("reflection.Person");
        System.out.println(aClass);

        var classLoader = ReflectionTest.class.getClassLoader();
        var aClass2 = classLoader.loadClass("reflection.Person");

        System.out.println(personClass == personClass2);
        System.out.println(personClass == aClass);
        System.out.println(personClass == aClass2);
    }

    @Test
    public void outlineClassLoader() {
        // 系统 类加载器 加载自定义类
        var sysClassLoader = ReflectionTest.class.getClassLoader();
        System.out.println(sysClassLoader);

        // 扩展 类加载器 加载 jre/lib/ext
        var extClassLoader = sysClassLoader.getParent();
        System.out.println(extClassLoader);

        // 引导 类加载器 加载 核心类
        // 但是获取不到
        var bootClassLoader = extClassLoader.getParent();
        System.out.println(bootClassLoader);

        var bootClassLoader0 = String.class.getClassLoader();
        System.out.println(bootClassLoader0);
    }

    @Test
    public void loadProperties() {
        final var  properties = new Properties();
//         从当前模组（module）读取文件📃
//        try (final var fileRd = new BufferedReader(new InputStreamReader(
//                new FileInputStream("jdbc.properties")))) {

//        从当前包下读取文件📃
        var classLoader = ReflectionTest.class.getClassLoader();
        try (final var fileRd = new BufferedReader(new InputStreamReader(Objects.requireNonNull(
                classLoader.getResourceAsStream("reflection/src-jdbc.properties"))))) {
            properties.load(fileRd);

            var user = properties.getProperty("name");
            var password = properties.getProperty("password");
            System.out.println("user: " + user);
            System.out.println("password:" + password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建运行时类的对象
     */
    @Test
    public void createRuntimeObj() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        var aClass = Person.class;
        var person = aClass.getDeclaredConstructor().newInstance();
        System.out.println(person);
    }

    private static Object getInstance(String classPath)
            throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        return Class.forName(classPath).getDeclaredConstructor().newInstance();
    }

    /**
     * ✨体现反射的动态性（运行时行为）
     */
    @Test
    public void dynamicNature() {
        var random = new Random();
        for (int i = 0; i < 10; i++) {
            var number = random.nextInt(3);
            var classPath = switch (number) {
                case 0 -> "java.lang.String";
                case 1 -> "java.util.Date";
                case 2 -> "reflection.Person";
                default -> "";
            };
            Object obj = null;
            try {
                obj = getInstance(classPath);
            } catch (ClassNotFoundException | NoSuchMethodException |
                    IllegalAccessException | InvocationTargetException |
                    InstantiationException e) {
                e.printStackTrace();
            }
            System.out.println(obj);
        }
    }

    /**
     * 🥇获得类型实参
     */
    @Test
    public void testParameterizedType() {
        var aClass = reflection.unique.Person.class;

        var paramType = (ParameterizedType) aClass.getGenericSuperclass();
        System.out.println(paramType);

        var actualTypeArgs = paramType.getActualTypeArguments();
        System.out.println(actualTypeArgs[0].getTypeName());
    }

    /**
     * 获得运行时类实现的所有接口
     */
    @Test
    public void getInterfaces() {
        final var aClass = reflection.unique.Person.class;
        final var interfaces = new ArrayList<Class<?>>();
        storeInterfacesOf(aClass, interfaces);
        for (var anInterface : interfaces) {
            System.out.println(anInterface);
        }
    }

    private void storeInterfacesOf(Class<?> aClass, List<Class<?>> interfaces) {
        if (null == aClass) return;
        storeInterfacesOf(aClass.getSuperclass(), interfaces);
        interfaces.addAll(Arrays.asList(aClass.getInterfaces()));
    }

    /**
     * 获得运行时类的对象的指定属性
     * <ol>
     *     <li>获得属性</li>
     *     <li>设置权限</li>
     *     <li>进行操作</li>
     * </ol>
     */
    @Test
    public void getCertainField() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException, NoSuchFieldException {
        final var aClass = Person.class;
        var person = (Person) aClass.getDeclaredConstructor().newInstance();

        var name = aClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "Rongrong");

        var nameValue = ((String) name.get(person));
        System.out.println(nameValue);

        var age = aClass.getDeclaredField("age");
        age.setAccessible(true);
        age.setInt(person, 1000);

        var idValue = age.getInt(person);
        System.out.println(idValue);
    }

    /**
     * 获得运行时类的对象的方法(也可以是静态方法，但是调用者最好是null[可以推导])
     * <ol>
     *     <li>获得方法</li>
     *     <li>设置权限</li>
     *     <li>调用方法接受返回值</li>
     * </ol>
     */
    @Test
    public void getCertainMethod() throws NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        final var aClass = Person.class;
        var person = aClass.getDeclaredConstructor().newInstance();

        // non-static method
        var showNation = aClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        var retValue = ((String) showNation.invoke(person, "🇨🇳"));
        System.out.println("Return value of showNation method: " + retValue);

        // static method
        var staticMethod = aClass.getDeclaredMethod("staticMethod");
        staticMethod.setAccessible(true);
        var nullValue = staticMethod.invoke(null);
        System.out.println("Return value of staticMethod method: " + nullValue);
    }

    /**
     * 利用反射和重载完成以下功能
     * <ol>
     *     <li>创建Student类，类中有属性name和age并封装属性</li>
     *     <li>重载Student的构造函数，一个是无参构造并，另一个是带两个参数的有参构造，要求在构造函数打印提示信息</li>
     *     <li>创建带main函数的NewInstanceTest类，利用Class类得到Student对象</li>
     *     <li>通过上述获取的Class对象分别调用Student有参函数和无参函数</li>
     * </ol>
     */
    @Test
    public void reflectStudentClass() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        var aClass = Class.forName("reflection.Student");
        var instance = ((Student) aClass.getDeclaredConstructor().newInstance());
        System.out.println(instance);

        var inst = (Student) aClass
                .getDeclaredConstructor(String.class, int.class)
                .newInstance("Tom", 12);
        System.out.println(inst);
    }

    /**
     * 利用反射的知识完成下面的操作
     * <p>创建Mytxt类，创建myCreate()方法完成创建文件D:\myhello.txt文件的功能。
     * 创建带main方法的NewInstanceTest类，通过Class类获取Mytxt对象，调用myCreat()</p>
     */
    @Test
    public void myTxt() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        var aClass = Class.forName("reflection.Mytxt");
        var inst = aClass.getDeclaredConstructor().newInstance();
        var myCreate = aClass.getDeclaredMethod("myCreate");
        myCreate.invoke(inst);
    }

    /**
     * 利用Annotation和反射知识完成操作
     * <ol>
     *     <li>自定义一个有效的Annotation注释名为MyAnnotation，其中有属性myname创建Student类并重写toString()，
     *     toString()要求使用三个基本的Annotation和自定义的MyAnnotation注释</li>
     *     <li>创建TestGetAnno类，打印出Student类的toString方法的所有注释</li>
     * </ol>
     */
    @Test
    public void testNote() throws NoSuchMethodException {
        var aClass = Student.class;
        var toString = aClass.getDeclaredMethod("toString");
        var notes = toString.getDeclaredAnnotations();
        for (var note : notes) {
            System.out.println(note);
        }
    }

    /**
     * 利用通过反射修改私有成员变量
     * <ol>
     *     <li>定义PrivateTest类，有私有name属性，并且属性值为hellokitty，只提供name的getName的公有方法</li>
     *     <li>创建带有main方法ReflectTest的类，利用Class类得到私有的name属性</li>
     *     <li>修改私有的name属性值，并调用getName()的方法打印name属性值</li>
     * </ol>
     */
    @Test
    public void modifyPrivateField() throws ClassNotFoundException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException,
            NoSuchFieldException {
        var aClass = Class.forName("reflection.PrivateTest");
        var inst = (PrivateTest) aClass.getDeclaredConstructor().newInstance();
        var name = aClass.getDeclaredField("name");
        // 🌟 设置权限
        name.setAccessible(true);
        name.set(inst, "hello");
        System.out.println(inst.getName());
    }

    /**
     * 利用反射和File完成以下功能
     * <ol>
     *     <li>利用Class类的forName方法得到File类</li>
     *     <li>在控制台打印File类的所有构造器</li>
     *     <li>通过newInstance的方法创建File对象，并创建D：\mynew.txt文件</li>
     * </ol>
     */
    @Test
    public void reflectAndFile() throws ClassNotFoundException, IOException, NoSuchMethodException,
            IllegalAccessException, InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        var fileClass = (Class<File>) Class.forName("java.io.File");
        var ctors = fileClass.getDeclaredConstructors();
        for (var ctor : ctors) {
            System.out.println(ctor);
        }

        var file = fileClass
                .getDeclaredConstructor(String.class)
                .newInstance("mynew.txt");
        //noinspection ResultOfMethodCallIgnored
        file.createNewFile();
    }
}
