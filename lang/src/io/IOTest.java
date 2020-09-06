package io;

import org.junit.Test;

import java.io.File;

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

        if (dir.exists() && (names = dir.list()) != null) {
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
        else if (file.isDirectory() && (files = file.listFiles()) != null) {
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
}
