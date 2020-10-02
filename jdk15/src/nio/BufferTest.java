package nio;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author huzihao
 * @since 2020/10/2 15:14
 */
public class BufferTest {
    /**
     * 常用方法
     * <ol>
     *     <li>allocate</li>
     *     <li>put</li>
     *     <li>get</li>
     *     <li>hasRemaining</li>
     *     <li>remaining(剩余可操作数据个数limit - position)</li>
     *     <li>flip(limit = position;position=0;mark=-1;)</li>
     *     <li>rewind(position = 0; mark = -1;)</li>
     *     <li>reset(position = mark)</li>
     *     <li>clear(遗忘而非擦除数据)</li>
     * </ol>
     * 常用属性
     * <ol>
     *     <li>capacity</li>
     *     <li>limit可操作数据的大小</li>
     *     <li>position</li>
     *     <li>mark和reset一起使用</li>
     * </ol>
     * mark <= position <= limit <= capacity
     */
    @Test
    public void testBuf() {
        var buf = ByteBuffer.allocate(1024);
        assert 1024 == buf.capacity();
        assert 1024 == buf.limit();
        assert 0 == buf.position();

        var data = "hello";
        buf.put(data.getBytes());
        assert 1024 == buf.capacity();
        assert 1024 == buf.limit();
        assert data.length() == buf.position();

        /*
        * limit = position
        * position = 0
        * mark = -1
        */
        buf.flip();
        assert 0 == buf.position();
        assert data.length() == buf.limit();
        assert 1024 == buf.capacity();

        var bytes = new byte[buf.limit()];
        buf.get(bytes);
        assert buf.position() == buf.limit();
        final var equals = data.equals(new String(bytes));
        assert equals;

        buf.rewind();
        buf.get(bytes, 0, 2);
        assert "he".equals(new String(bytes, 0, 2));
        buf.mark();
        buf.get(bytes, 0, 3);
        assert "llo".equals(new String(bytes, 0, 3));
        buf.reset();
        buf.get(bytes, 0, 2);
        assert "ll".equals(new String(bytes, 0, 2));

        buf.clear();
        assert 0 == buf.position();
        assert 1024 == buf.limit();
    }

    // TODO: 2020/10/2 乱码
    @Test
    public void readLoop() {
        var buf = ByteBuffer.allocate(1024);
        buf.put("hello from the outside!".getBytes());

        for (var bytes = new byte[10]; buf.hasRemaining(); ) {
            var length = Math.min(buf.remaining(), bytes.length);
            buf.get(bytes, 0, length);
            System.out.print(new String(bytes, 0, length, StandardCharsets.UTF_8));
        }
    }

    @Test
    public void directBuffer() {
        var buf = ByteBuffer.allocateDirect(1024);
        assert buf.isDirect();
    }

    @Test
    public void copyFileUsingChannel() {
        try (var fin = new FileInputStream("Luffy.jpg");
             var fout = new FileOutputStream("Lucy.jpg");
             var finChannel = fin.getChannel();
             var foutChannel = fout.getChannel()) {
            var buffer = ByteBuffer.allocate(1024);
            while (-1 != finChannel.read(buffer)) {
                buffer.flip();
                foutChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void memoryMapperFile() {
        try (var finChannel = FileChannel.open(Paths.get("Luffy.jpg"),
                StandardOpenOption.READ);
             var foutChannel = FileChannel.open(Paths.get("Lucy2.jpg"),
                     StandardOpenOption.READ, StandardOpenOption.WRITE,
                     StandardOpenOption.CREATE)) {
            var mappedFinBuffer = finChannel.map(FileChannel.MapMode.READ_ONLY,
                    0, finChannel.size());
            var mappedFoutBuffer = foutChannel.map(FileChannel.MapMode.READ_WRITE,
                    0, finChannel.size());
            var buffer = new byte[mappedFinBuffer.limit()];
            mappedFinBuffer.get(buffer);
            mappedFoutBuffer.put(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void transformWithChannel() {
        try (var finChannel = FileChannel.open(Paths.get("Luffy.jpg"),
                StandardOpenOption.READ);
             var foutChannel = FileChannel.open(Paths.get("Lucy3.jpg"),
                     StandardOpenOption.READ, StandardOpenOption.WRITE,
                     StandardOpenOption.CREATE)) {
            finChannel.transferTo(0, finChannel.size(), foutChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
