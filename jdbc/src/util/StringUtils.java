package util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author huzihao
 * @since 2020/9/25 17:33
 */
public final class StringUtils {
    public static Set<String> getKeywords() {
        var hashSet = new HashSet<String>();
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

    /**
     * Change snake style identifier to camelCase
     * @param identifier identifier
     * @return camelCase style identifier
     */
    public static String snakeToCamel(String identifier) {
        // 不处理 关键字\_前缀\_后缀\仅有_的字符串
        if (StringUtils.getKeywords().contains(identifier) ||
                identifier.startsWith("_") || identifier.endsWith("_")) {
            throw new IllegalArgumentException("Won't process keywords, prefix of _, suffix of _ " +
                    "and string which only contains _");
        }

        final var builder = new StringBuilder();
        var hasUnderscore = false;
        for (var i = -1; -1 != (i = identifier.indexOf("_", i + 1)) &&
                i < identifier.length() - 1; ) {
            hasUnderscore = true;
            builder.append(identifier, 0, i)
                    .append(Character.toUpperCase(identifier.charAt(i + 1)))
                    .append(identifier.substring(i + 2));
        }
        return hasUnderscore ? builder.toString() : identifier;
    }
}
