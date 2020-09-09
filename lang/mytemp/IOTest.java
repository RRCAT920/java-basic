package io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import io.record.Person;

/**
 * @author huzihao
 * @since 2020/9/6 11:46
 */
public class IOTest {
    @Test
    public void fileQueryMethod() {
        var relativeFile = new File("hello.txt");
        var absoluteFile = new File(
                "/Users/huzihao/IdeaProjects/java-basic/lang/src/io/IOTest.java");

        assert "/Users/huzihao/IdeaProjects/java-basic/lang/hello.txt"
                .equals(relativeFile.getAbsolutePath());
        assert "hello.txt".equals(relativeFile.getPath());
        assert "hello.txt".equals(relativeFile.getName());
        assert null == relativeFile.getParent();
        assert 0 == relativeFile.length();
        assert 0 == relativeFile.lastModified();

        assert absoluteFile.getPath().equals(absoluteFile.getAbsolutePath());
        assert "/Users/huzihao/IdeaProjects/java-basic/lang/src/io"
                .equals(absoluteFile.getParent());
        assert "IOTest.java".equals(absoluteFile.getName());
        System.out.println(absoluteFile.length());
        System.out.println(absoluteFile.lastModified());
    }

    /**
     * 最佳实践🥇: exits()
     */
    @Test
    public void fileQueryMethod1() {
        var relativeFile = new File("name.txt");

        assert !relativeFile.isDirectory();
        assert relativeFile.isFile();
        assert relativeFile.exists();
        assert relativeFile.canRead();
        assert relativeFile.canWrite();
        assert !relativeFile.isHidden();

        relativeFile = new File("name1.txt");
        assert !relativeFile.isDirectory();
        assert !relativeFile.isFile(); // watch me
        assert !relativeFile.exists();
        assert !relativeFile.canRead();
        assert !relativeFile.canWrite();
        assert !relativeFile.isHidden();
    }

    @Test
    public void jpgFilter() {
        var dir = new File(".");
        String[] names;

        if (dir.exists() && null != (names = dir.list())) {
            for (var name : names) {
                if (name.endsWith(".jpg")) System.out.println(name);
            }
        }
    }

    /**
     * ❌❌❌❌❌❌❌❌❌❌
     * Don't run this code.
     * It will remove enumclass directory.
     */
    @Test
    public void name() {
        var dir = new File("./src/enumclass");
        System.out.println("❌占用空间：" + dir.length());
        System.out.println("占用空间：" + size(dir));
        traverseFile(dir);
        removeAllFilesOf(dir);
    }

    private void traverseFile(File file) {
        if (!file.exists()) return;
        if (file.isFile()) {
            System.out.println(file.getName());
            return;
        }

        var files = file.listFiles();

        if (null == files) return; // 空目录

        for (var aFile : files) {
            traverseFile(aFile);
        }
    }

    private long size(File file) {
        long size = 0;
        File[] files;

        if (file.isFile()) size = file.length();
        else if (file.isDirectory() && null != (files = file.listFiles())) {
            for (var aFile : files) {
                size += size(aFile);
            }
        }

        return size;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void removeAllFilesOf(File dir) {
        if (!dir.exists()) return;
        File[] files;

        if ((files = dir.listFiles()) != null) {
            for (var aFile : files) {
                removeAllFilesOf(aFile);
            }
        }
        dir.delete(); // suppress warning
    }

    /**
     * File类提供了两个文件过滤器方法<p>
     * public String[] list(FilenameFilter filter)<p>
     * public File[] listFiles(FileFilter filter)
     */
    @Test
    public void test3() {
        var srcFile = new File(".");
        var names = srcFile.list((dir, name) ->
                name.endsWith(".jpg") || name.endsWith(".jpeg"));

        if (names != null) {
            for (var name : names) {
                System.out.println(name);
            }
        }
    }

    /**
     * <ol>
     *     <li>read(): int 每次读取一个字符，文件尾返回-1</li>
     *     <li>为了保证一定能关闭流，用try-catch-finally处理异常</li>
     *     <li>要读的文件不存在则有FileNotFoundException</li>
     * </ol>
     */
    @Test
    public void fileReader() {
        /*
        相对路径：
            main方法中的开始于工程目录
            单元测试中开始于模组目录
         */
        var file = new File("name.txt");

        // 只有可能因为其他原因抛出异常
        if (file.isFile() && file.canRead()) {
            FileReader fileReader = null;
            try {
                fileReader = new FileReader(file);

                int aChar;
                while ((aChar = fileReader.read()) != -1) {
                    System.out.print((char) aChar);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Test
    public void fileReaderWithCharBuffer() {
        var file = new File("names11111.txt");

        if (file.isFile() && file.canRead()) {
            FileReader fileReader = null;

            try {
                fileReader = new FileReader(file);
                var charBuffer = new char[5];
                int size;

                while ((size = fileReader.read(charBuffer)) != -1) {
//                    System.out.println("size: " + size);
                    var str = new String(charBuffer, 0, size);
                    System.out.println(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fileReader != null) {
                    try {
                        fileReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * <ol>
     *     <li>要写的文件不存在则创建文件</li>
     *     <li>可以对文件内容进行覆盖或追加</li>
     * </ol>
     */
    @Test
    public void fileWriter() {
        var file = new File("names.txt");

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            fileWriter.write("hello\n");
            fileWriter.write("world!\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void copyFile() {
        var srcFile = new File("names.txt");
        var destFile = new File("names2.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;

        try {
            fileReader = new FileReader(srcFile);
            fileWriter = new FileWriter(destFile);

            var charBuffer = new char[5];
            int size;
            while ((size = fileReader.read(charBuffer)) != -1) {
                fileWriter.write(charBuffer, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void copyFileSpeedUp() {
        var srcFile = new File("Luffy.jpg");
        var destFile = new File("Lucy.jpg");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            var fis = new FileInputStream(srcFile);
            var fos = new FileOutputStream(destFile);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            var buffer = new byte[10];
            var length = 0;

            while (-1 != (length = bis.read(buffer))) {
                bos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void encryptImage() {
        cryptFileDually("Luffy.jpg", "Encrypted-Lucy.jpg");
    }

    @Test
    public void decryptImage() {
        cryptFileDually("Encrypted-Lucy.jpg", "Decrypted-Lucy.jpg");
    }

    private static final int MASK = 5;

    private void cryptFileDually(String src, String dest) {
        var srcFile = new File(src);
        var destFile = new File(dest);
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            var fis = new FileInputStream(srcFile);
            var fos = new FileOutputStream(destFile);
            bis = new BufferedInputStream(fis);
            bos = new BufferedOutputStream(fos);
            var buffer = new byte[10];
            var length = 0;

            while (-1 != (length = bis.read(buffer))) {
                for (int i = 0; i < length; i++) {
                    buffer[i] ^= MASK;
                }
                bos.write(buffer, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != bos) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != bis) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void countChar() {
        var srcFile = new File("src/io/IOTest.java");
        var destFile = new File("countChar.txt");

        if (srcFile.isDirectory() || !srcFile.canRead() ||
                destFile.isFile() && !destFile.canWrite()) return;

        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            var fr = new FileReader(srcFile);
            var fw = new FileWriter(destFile);
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);
            var chars = new char[1024];
            var length = 0;
            var map = new HashMap<Character, Integer>();

            while (-1 != (length = br.read(chars))) {
                for (int i = 0; i < length; i++) {
                    var c = chars[i];
                    if (map.containsKey(c)) {
                        map.put(c, map.get(c) + 1);
                        continue;
                    }
                    map.put(c, 1);
                }
            }

            for (var entry : map.entrySet()) {
                var key = entry.getKey() + "";

                key = switch (key) {
                    case " " -> "空格";
                    case "\t" -> "制表符";
                    case "\r" -> "回车";
                    case "\n" -> "换行";
                    default -> key;
                };
                bw.write(key + "=" + entry.getValue());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * @throws IOException 本应该用try-catch-finally
     */
    @Test
    public void inputStreamToReader() throws IOException {
        var fis = new FileInputStream("countChar.txt");
        var isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        var cbuf = new char[20];
        int length;

        while (-1 != (length = isr.read(cbuf))) {
            var str = new String(cbuf, 0, length);
            System.out.print(str);
        }

        isr.close();
    }

    /**
     * @throws IOException 本应该用try-catch-finally
     */
    @Test
    public void OutputStreamWriter() throws IOException {
        var srcFile = new File("dbcp.txt");
        var destFile = new File("dbcp-gbk.txt");

        var fis = new FileInputStream(srcFile);
        var fos = new FileOutputStream(destFile);
        var isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
        var osw = new OutputStreamWriter(fos, "gbk");

        var cbuf = new char[20];
        int length;

        while (-1 != (length = isr.read(cbuf))) {
            osw.write(cbuf, 0, length);
        }

        osw.close();
        ;
        isr.close();
    }

    /**
     * <p>
     * 从键盘输入字符串，要求将读取到的整行字符串转成大写输出。然后继续进行输入操作。直到输入"e"或者"exit"时，退出程序。
     * </p>
     * <ol>
     *     <li>Scanner</li>
     *     <li>System.in & InputStreamReader & BufferedReader</li>
     * </ol>
     */
    public void eOrExit() throws IOException {
        var isr = new InputStreamReader(System.in);
        var br = new BufferedReader(isr);
        String line;

        while (true) {
            System.out.println("请输入字符串：");
            line = br.readLine();

            if ("e".equalsIgnoreCase(line) || "exit".equalsIgnoreCase(line)) break;
            System.out.println(line.toUpperCase());
        }
    }

    public static void main(String[] args) {
        var str = MyInput.readString();
        System.out.println(str);

        var number = MyInput.nextInt();
        System.out.println(number);
    }

    /**
     * <p>Create a class named MyInput:</p>
     * <p>Contain the methods for reading int, double, float, boolean, short, byte and String values
     * from the keyboard.</p>
     */
    static class MyInput {
        /**
         * fixme 不能同行读取
         * @return 读取结果
         */
        public static String next() {
            var br = new BufferedReader(new InputStreamReader(System.in));

            var strBuilder = new StringBuilder();
            int c;
            try {
                flag: while (true) {
                    c = br.read();
                    switch (c) {
                        case -1, ' ', '\t', '\n', '\r' -> {
                            break flag;
                        }
                        default -> strBuilder.append((char) c);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return strBuilder.toString();
        }

        // Read a string from the keyboard
        public static String readString() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            // Declare and initialize the string
            String string = "";

            // Get the string from the keyboard
            try {
                string = br.readLine();

            } catch (IOException ex) {
                System.out.println(ex);
            }

            // Return the string obtained from the keyboard
            return string;
        }

        public static byte nextByte() {
            return Byte.parseByte(readString());
        }

        public static short nextShort() {
            return Short.parseShort(readString());
        }

        public static int nextInt() {
            return Integer.parseInt(readString());
        }

        public static long nextLong() {
            return Long.parseLong(readString());
        }

        public static float nextFloat() {
            return Float.parseFloat(readString());
        }

        public static double nextDouble() {
            return Double.parseDouble(readString());
        }

        public static boolean nextBoolean() {
            return Boolean.parseBoolean(readString());
        }
    }

    @Test
    public void printStream() {
        PrintStream ps = null;
        try {
            var fos = new FileOutputStream("ascii.txt");
            ps = new PrintStream(fos, true);
            System.setOut(ps);

            for (int i = 0; i < 256; i++) {
                System.out.print((char) i);
                if ((i + 1) % 50 == 0) System.out.println();// 每50个换一次行的技巧
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
    }

    @Test
    public void dataOutputStream() throws IOException {
        var dos = new DataOutputStream(new FileOutputStream("data.txt"));

        dos.writeUTF("李容蓉");
        dos.writeInt(23);
        dos.writeBoolean(true);

        dos.close();
    }

    @Test
    public void dataInputStream() throws IOException {
        var dis = new DataInputStream(new FileInputStream("data.txt"));

        // 读顺序要和写顺序一致，否则抛出EOFException
        System.out.println(dis.readUTF());
        System.out.println(dis.readInt());
        System.out.println(dis.readBoolean());

        dis.close();
    }

    @Test
    public void serialize() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("serialization.dat"));

            oos.writeObject("每当夜幕降临的时候，我得扣紧纽扣。");
            oos.flush();

            oos.writeObject(new Person("李容蓉", 21));
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void deserialize() {
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("serialization.dat"));

            var str = (String) ois.readObject();
            System.out.println(str);

            var person = (Person) ois.readObject();
            System.out.println(person);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != ois) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 1.RandomAccessFile可以作为输入流或输出流（直接继承Object，实现接口DataInput和DataOutput）
     * 2.写文件时，若文件存在，则从头覆盖
     */
    @Test
    public void randomAccess() {
        var file = new File("random-access.txt");
        RandomAccessFile inRaf = null;
        RandomAccessFile outRaf = null;
        try {
            inRaf = new RandomAccessFile(file, "r");
            outRaf = new RandomAccessFile(file, "rw");

            var buffer = new byte[20];
            int length;
            while (-1 != (length = inRaf.read(buffer))) {
                System.out.println(new String(buffer, 0, length));
            }

            outRaf.write("hello///".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != outRaf) {
                try {
                    outRaf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != inRaf) {
                try {
                    inRaf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void insertWithBuilder() {
        var file = new File("random-access.txt");
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(file, "rw");

            long pos = 3;
            var buffer = new byte[20];
            int length;
            var strBuilder = new StringBuilder((int) (file.length() - pos));

            raf.seek(pos);
            while (-1 != (length = raf.read(buffer))) {
                strBuilder.append(new String(buffer, 0, length));
            }

            raf.seek(pos);
            raf.write("<insert text>".getBytes());
            raf.write(strBuilder.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != raf) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @Test
    public void insertWithByteArrayOutputStream() {
        var file = new File("random-access.txt");
        RandomAccessFile raf = null;
        ByteArrayOutputStream baos = null;
        try {
            raf = new RandomAccessFile(file, "rw");

            long pos = 3;
            var buffer = new byte[20];
            int length = 0;
            baos = new ByteArrayOutputStream((int) (file.length() - pos));

            raf.seek(pos);
            while (-1 != (length = raf.read(buffer))) {
                baos.write(buffer, 0, length);
            }

            raf.seek(pos);
            raf.write("<insert text with ByteArrayOutputStream>".getBytes());
            raf.write(baos.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != baos) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != raf) {
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 1. 在指定的路径下新建一个 .txt 文件 "test.txt"，利用程序在文件中写入如下内容：
     * <p>
     * "Java是一种可以撰写跨平台应用软件的面向对象的程序设计语言，
     * 是由Sun Microsystems公司于 1995年5月推出的Java程序设计语言和Java平台（即JavaSE, JavaEE, JavaME）的总称。
     * Java 技术具有 卓越的通用性、高效性、平台移植性和安全性，广泛应用于个人PC、数据中心、游戏控制台、科 学超级计算机、
     * 移动电话和互联网，同时拥有全球最大的开发者专业社群。在全球云计算和移动互 联网的产业环境下，
     * Java更具备了显著优势和广阔前景。"
     */
    @Test
    public void writeCh() {
        var file = new File("test.txt");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);

            fileWriter.write("Java是一种可以撰写跨平台应用软件的面向对象的程序设计语言，" +
                    "是由Sun Microsystems公司于 1995年5月推出的Java程序设计语言和Java平台" +
                    "（即JavaSE, JavaEE, JavaME）的总称。Java 技术具有 卓越的通用性、高效性、" +
                    "平台移植性和安全性，" +
                    "广泛应用于个人PC、数据中心、游戏控制台、科 学超级计算机、移动电话和互联网，" +
                    "同时拥有全球最大的开发者专业社群。在全球云计算和移动互 联网的产业环境下，" +
                    "Java更具备了显著优势和广阔前景。");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 2. 利用程序读取 test.txt 文件的内容, 并在控制台打印
     */
    @Test
    public void readFile() {
        var file = new File("test.txt");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);

            var chars = new char[1024];
            int length;
            while (-1 != (length = fileReader.read(chars))) {
                var str = new String(chars, 0, length);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 3. 利用程序复制 test.txt 为 test1.txt
     */
    @Test
    public void cpFile() {
        var file = new File("test.txt");
        var destFile = new File("test1.txt");
        FileReader fileReader = null;
        FileWriter fileWriter = null;
        try {
            fileReader = new FileReader(file);
            fileWriter = new FileWriter(destFile);

            var chars = new char[1024];
            int length;
            while (-1 != (length = fileReader.read(chars))) {
                var str = new String(chars, 0, length);
                fileWriter.write(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fileWriter) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != fileReader) {
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 4. 列出当前目录下全部java文件的名称
     */
    @Test
    public void listAllJavaFile() {
        var file = new File("src/io");
        String[] names;

        if (file.isDirectory() && null != (names = file.list())) {
            for (var name : names) {
                if (name.endsWith(".java")) System.out.println(name);
            }
        }
    }

    /**
     * 5. 列出当前目录下Java源文件的名字及其大小，并删除其中的一个Java源文件？
     */
    @Test
    public void listAndDelete() {
        var file = new File("src/io");
        File[] files;

        if (file.isDirectory() && null != (files = file.listFiles())) {
            for (var aFile : files) {
                var name = aFile.getName();
                if (name.endsWith(".java")) System.out.println(name + ":" + aFile.length());
                if ("Hello.java".equals(name)) aFile.delete();
            }
        }
    }

    /**
     * 6. 使用File类下的常用方法获取某些文件的信息。
     */
    @Test
    public void FileMethod() {
        var file = new File("src/io/IOTest.java");

        System.out.println("是文件？" + file.isFile());
        System.out.println("是目录？" + file.isDirectory());
        System.out.println("存在？" + file.exists());
        System.out.println("可读写？" + (file.canRead() && file.canWrite()));
        System.out.println("隐藏文件？" + file.isHidden());

        System.out.println("绝对路径:" + file.getAbsolutePath());
        System.out.println("相对路径:" + file.getPath());
        System.out.println("文件名:" + file.getName());
        System.out.println("父目录:" + file.getParent());
        System.out.println("文件大小:" + file.length());
        System.out.println("最近修改时间:" + file.lastModified());
    }

    /**
     * 7. 操作模块下的my.txt文件
     * 判断my.txt文件是否存在
     * 若存在则删除;若不存在则创建
     */
    @Test
    public void myTxt() {
        var file = new File("my.txt");

        if (file.exists()) file.delete();
        else {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 9. 使用File类删除某个文件夹（例如D盘下的temp文件夹）下的所有文件和文件夹:
     * 判断temp文件夹下的文件类型，如果是文件则直接删除
     * 如果是文件夹则获取该文件夹下的子文件和文件夹
     * 使用递归的方式删除所有temp文件夹下的文件和文件夹
     */
    @Test
    public void testDeleteAll() {
        deleteAll(new File("delete"));
    }

    private void deleteAll(File file) {
        if (!file.exists()) return;

        File[] files;
        if (file.isDirectory() && null != (files = file.listFiles())) {
            for (var aFile : files) {
                deleteAll(aFile);
            }
        }

        file.delete();
    }

    /**
     * 10. 利用IO操作文件
     * 1)利用代码在当前目录下创建news文件夹。
     * 2)利用代码在news文件夹下创建2个.docx文件，2个.java文件，2个.txt的文件
     * （在控制台打印news文件夹下的.java文件
     */
    @Test
    public void createDirAndFiles() {
        var dir = new File("news");

        if (!dir.exists()) dir.mkdir();

        String[] names = {".docx", ".java", ".txt"};
        for (int i = 0; i < 3; i++) {
            var file1 = new File(dir, 1 + names[i]);
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            var file2 = new File(dir, 2 + names[i]);
            try {
                file2.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (1 == i) {
                System.out.println(file1);
                System.out.println(file2);
            }
        }
    }

    /**
     * 11. 利用IO流操作文件
     * 1)利用Java代码创建temp\temp1\temp2共3个文件夹
     * 2)在temp文件夹下创建一个1.txt文件内容为hello，创建一个Hello.java文件
     * 3)内容为public static void main(String[] args){},在temp1文件夹下创建同样的两个文件
     * 4)输出temp文件夹下包括其子文件夹下，所有的.java文件
     */
    @Test
    public void createTemp() {
        var dir = new File("temp/temp1/temp2");

        dir.mkdirs();
    }

    /**
     * 12. 利用IO流操作文件
     * 利用java代码在D盘下创建一个mytemp文件夹
     * 显示D盘下所有的.Java文件，包括D盘的子文件夹下的.java文件
     * 把上述显示的文件都复制到mytemp文件夹中
     */
    @Test
    public void myTemp() {
        var destFile = new File("mytemp");
        var srcFile = new File("src/io");

        copyToDirectory(srcFile, destFile, ".java");
    }

    private void copyToDirectory(File src, File dest, String pattern) {
        var files = src.listFiles();
        if (null != files) {
            for (var file : files) {
                var name = file.getName();
                if (file.isDirectory()) copyToDirectory(file, dest, pattern);
                else if (name.endsWith(pattern)) {
                    copyFile(file, dest);
                }
            }
        }
    }

    private void copyFile(File src, File dest) {
        dest.mkdir();
        var file = new File(dest, src.getName());

        try (var fileReader = new FileReader(src);
             var fileWriter = new FileWriter(file);
             var bufferedReader = new BufferedReader(fileReader);
             var bufferedWriter = new BufferedWriter(fileWriter)) {
            file.createNewFile();

            String str;
            while (null != (str = bufferedReader.readLine())) {
                bufferedWriter.write(str);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 13. 列出D盘下的所有文件，子目录中的文件，子目录的子目录都要列出，依次类推，总之D盘下所有的文件都要输出（50分）
     * 在列出的时候判断是否子文件夹（10分）
     * 若不是子文件夹直接输出文件名（20分）
     * 若是子文件夹使用递归的形式继续输出子目录中的文件（20分）
     */
    /**
     * 14. 先将“欢迎您来北京学习!”写入到文件”hello.txt”中，再读取该文件中的内容。
     */
    /**
     * 15. 如果准备读取一个文件的内容，应当使用FileInputStream流还是FileOutputStream流？
     */
    /**
     * 16. 编写一个应用程序，将用户从键盘输入的10个整数存入文件，再顺序读出。
     */
    /**
     * 17. BufferedReader流能直接指向一个文件对象吗？
     */
    /**
     * 18. 列出D盘下的所有文件，子目录中的文件，子目录的子目录都要列出，依次类推，总之D盘下所有的文件都要输出。
     */
    /**
     * 19. 编写程序向文本文件中写入自己的信息，格式为：姓名：XXX 性别：X 年龄：XX 班级：XXX，
     * 将该信息读出后显示的屏幕上后把文件删除。
     */
    /**
     * 20. 用缓冲字节流实现文件复制的功能
     * 首先判断d盘是否存在a.txt文件。
     * 若不存在则创建a.txt文件，然后把a.txt文件复制成acopy.txt
     */
    /**
     * 21. 通过Io流实现MP3文件创建和复印功能
     * 判断d:/歌曲.mp3文件是否存在
     * 若不存在则创建d:/歌曲.mp3文件，创建完成后复制到 e:/歌曲.mp3
     */
    /**
     * 22. 用（字节流）读取一张图片，读进来之后再输出到另一个文件中。
     */
    /**
     * 23. （字符流）读取一个文本文件，每行都追加一个“好”，在文件结尾再追加“完毕”。
     */
    /**
     * 24. 使用Java的输入、输出流将一个文本文件的内容按行读出，每读出一行就顺序添加行号，并写入到另一个文件中。
     */
    /**
     * 25. 使用RandomAccessFile流将一个文本文件倒置读出。
     */
    /**
     * 26. 使用ObjectInputStream类和ObjectOutputStream类有哪些注意事项？。
     */
    /**
     * 27. 用缓冲字符流读入一个文件中的内容，并把内容输出到一个新的文件中。
     */
    /**
     * 28．使用输入流读取试题文件，每次显示试题文件中的一道题目。读取到字符“*”时暂停读取，等待用户从键盘输入答案。
     * 用户做完全部题目后。程序给出用户的得分。
     * 试题内容如下：
     * (1)北京奥运是什么时间开幕的？
     *    A.2008-08-08  B. 2008-08-01
     * C.2008-10-01 D. 2008-07-08
     * ********************
     * (2)下列哪个国家不属于亚洲？
     *    A.沙特  B.印度 C.巴西  D.越南
     * ********************
     * (3)下列哪个国家最爱足球？
     *    A.刚果  B.越南 C.老挝  D.巴西
     * ********************
     * (4)下列哪种动物属于猫科动物？
     *    A.鬣狗  B.犀牛 C.大象 D.狮子
     * ********************
     *
     * 2）程序运行如下：
     * (1)北京奥运是什么时间开幕的？
     *    A.2008-08-08  B. 2008-08-01
     * C.2008-10-01 D. 2008-07-08
     * 输入选择的答案（A、B、C、D）：A
     * (2)下列哪个国家不属于亚洲？
     *    A.沙特  B.印度 C.巴西  D.越南
     * 输入选择的答案（A、B、C、D）：
     */
}
