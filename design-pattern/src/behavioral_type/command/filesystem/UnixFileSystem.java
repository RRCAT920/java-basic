package behavioral_type.command.filesystem;

/**
 * @author huzihao
 * @since 2020/11/9 21:01
 */
public class UnixFileSystem implements FileSystem {
    @Override
    public void openFile() {
        System.out.println("在Unix系统中打开文件");
    }

    @Override
    public void writeFile() {
        System.out.println("在Unix系统中写文件");
    }

    @Override
    public void closeFile() {
        System.out.println("在Unix系统中关闭文件");
    }
}
