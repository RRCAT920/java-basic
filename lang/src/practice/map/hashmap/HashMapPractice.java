package practice.map.hashmap;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * @author huzihao
 * @since 2020/9/4 23:03
 */
public class HashMapPractice {
    /**
     * 姓氏统计：一个文本文件中存储着北京所有高校在校生的姓名，格式如下：
     * 每行一个名字，姓与名以空格分隔：
     * 张 三
     * 李 四
     * 王 小五
     * 现在想统计所有的姓氏在文件中出现的次数，请描述一下你的解决方案。
     */
    @Test
    public void lastNameCountWithHashMap() {
        // 读文件
        // 增加
        var map = new HashMap();
        var file = new File("name.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (in != null) {
            while (in.hasNext()) {
                var lastName = in.nextLine().charAt(0);
                if (map.containsKey(lastName)) {
                    int number = (Integer) map.get(lastName) + 1;
                    map.put(lastName, number);
                    continue;
                }
                map.put(lastName, 1);
            }
        }

        System.out.println(map);
    }

    @Test
    public void lastNameCountWithArrayList() {
        var lastNames = new ArrayList();
        var file = new File("name.txt");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var result = new StringBuilder();

        if (in != null) {
            while (in.hasNext()) {
                var lastName = in.nextLine().charAt(0);
                lastNames.add(lastName);
            }

            var surnames = new HashSet(lastNames);
            result.append("{");
            for (Object surname : surnames) {
                result.append(surname)
                        .append("=")
                        .append(Collections.frequency(lastNames, surname))
                        .append(", ");
            }
            result.replace(result.length() - 2, result.length(), "}");
        }

        System.out.println(result);
    }

    /**
     * 对一个Java源文件中的关键字进行计数
     */
    @Test
    public void KeywordsCounter() {
        var file = new File("src/practice/map/hashmap/HashMapPractice.java");
        Scanner in = null;
        try {
            in = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        var map = new HashMap();
        var keywords = keywordsHashSet();

        if (in != null) {
            while (in.hasNext()) {
                var word = in.next();
                if (keywords.contains(word)) {
                    if (map.containsKey(word)) {
                        map.put(word, (Integer) map.get(word) + 1);
                        continue;
                    }
                    map.put(word, 1);
                }
            }
        }

        System.out.println(map);
    }

    private HashSet keywordsHashSet() {
        var hashSet = new HashSet();
//        abstract   continue   for          new         switch
        {
            hashSet.add("abstract");
            hashSet.add("continue");
            hashSet.add("for");
            hashSet.add("new");
            hashSet.add("switch");
        }
//        assert     default    if           package     synchronized
        {
            hashSet.add("assert");
            hashSet.add("default");
            hashSet.add("if");
            hashSet.add("package");
            hashSet.add("synchronized");
        }
//        boolean    do         goto         private     this
        {
            hashSet.add("boolean");
            hashSet.add("do");
            hashSet.add("goto");
            hashSet.add("private");
            hashSet.add("this");
    }
//        break      double     implements   protected   throw
        {
            hashSet.add("break");
            hashSet.add("double");
            hashSet.add("implements");
            hashSet.add("protected");
            hashSet.add("throw");
        }
//        byte       else       import       public      throws
        {
            hashSet.add("byte");
            hashSet.add("else");
            hashSet.add("import");
            hashSet.add("public");
            hashSet.add("throws");
        }
//        case       enum       instanceof   return      transient
        {
            hashSet.add("case");
            hashSet.add("enum");
            hashSet.add("instanceof");
            hashSet.add("return");
            hashSet.add("transient");
        }
//        catch      extends    int          short       try
        {
            hashSet.add("catch");
            hashSet.add("extends");
            hashSet.add("int");
            hashSet.add("short");
            hashSet.add("try");
        }
//        char       final      interface    static      void
        {
            hashSet.add("char");
            hashSet.add("final");
            hashSet.add("interface");
            hashSet.add("static");
            hashSet.add("void");
        }
//        class      finally    long         strictfp    volatile
        {
            hashSet.add("class");
            hashSet.add("finally");
            hashSet.add("long");
            hashSet.add("strictfp");
            hashSet.add("volatile");
        }
//        const      float      native       super       while
//        _ (underscore)
        {
            hashSet.add("const");
            hashSet.add("float");
            hashSet.add("native");
            hashSet.add("super");
            hashSet.add("while");
            hashSet.add("_");
        }

        return hashSet;
    }
}
