package io;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

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

        for (var name : names) {
            System.out.println(name);
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
    public void fileWriter() throws IOException {
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
            var map = new HashMap<String, Integer>();

            while (-1 != (length = br.read(chars))) {
                for (int i = 0; i < length; i++) {
                    var c = chars[i];
                    var key = c == '\n' ? "\\n" : c + "";
                    if (map.containsKey(key)) {
                        map.put(key, map.get(key) + 1);
                        continue;
                    }
                    map.put(key, 1);
                }
            }

            for (var entry : map.entrySet()) {
                bw.write(entry.getKey() + "=" + entry.getValue());
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

    @Test
    public void teachRRCAT() {
        System.out.println("容蓉小猫咪🐈");
    }
}