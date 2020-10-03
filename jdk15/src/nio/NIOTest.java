package nio;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

/**
 * @author huzihao
 * @since 2020/10/2 15:14
 */
public class NIOTest {
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

    @Test
    public void readLoop() {
        var buf = ByteBuffer.allocate(1024);
        buf.put("hello from the outside!".getBytes());
        buf.flip();
        System.out.println(new String(buf.array(), 0, buf.limit()));
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
            var finMappedBuffer = finChannel.map(FileChannel.MapMode.READ_ONLY,
                    0, finChannel.size());
            var foutMappedBuffer = foutChannel.map(FileChannel.MapMode.READ_WRITE,
                    0, finChannel.size());
            var buffer = new byte[finMappedBuffer.limit()];
            finMappedBuffer.get(buffer);
            foutMappedBuffer.put(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 直接缓冲区
     */
    @Test
    public void transformWithChannel() {
        try (var finChannel = FileChannel.open(Paths.get("Luffy.jpg"),
                StandardOpenOption.READ);
             var foutChannel = FileChannel.open(Paths.get("Lucy3.jpg"),
                     StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            finChannel.transferTo(0, finChannel.size(), foutChannel);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void ScatterAndGather() {
         try (var srcFile = new RandomAccessFile("Luffy.jpg", "r");
              var destFile = new RandomAccessFile("Lucy4.jpg", "rw");
              var finChannel = srcFile.getChannel();
              var foutChannel = destFile.getChannel()) {
             var buffers = IOUtils.getByteBuffers(1000, 1024);
             while (-1 != finChannel.read(buffers)) {
                 Arrays.stream(buffers).forEach(ByteBuffer::flip);
                 foutChannel.write(buffers);
                 Arrays.stream(buffers).forEach(ByteBuffer::clear);
             }
         } catch (IOException e) {
             e.printStackTrace();
         }
    }

    @Test
    public void testCharset() {
        var charsets = Charset.availableCharsets();
        charsets.forEach((name, charset) -> System.out.println(name + "=" + charset));
    }

    @Test
    public void encodeAndDecode() throws CharacterCodingException {
        var gbk = Charset.forName("GBK");
        var encoder = gbk.newEncoder();
        var decoder = gbk.newDecoder();
        var charBuffer = CharBuffer.allocate(1024);

        charBuffer.put("你好啊");
        charBuffer.flip();
        var byteBuffer = encoder.encode(charBuffer);
        System.out.println(byteBuffer);
        var charBufferFrom = decoder.decode(byteBuffer);
        System.out.println(charBufferFrom);
    }

    @Test
    public void BlockingServer() {
        try (var serverSocketChannel = ServerSocketChannel.open()
                .bind(new InetSocketAddress(8000));
             var socketChannel = serverSocketChannel.accept();
             var foutChannel = FileChannel.open(Paths.get("server-Luffy.jpg"),
                     StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            var buffer = ByteBuffer.allocate(1024);
            while (-1 != socketChannel.read(buffer)) {
                buffer.flip();
                foutChannel.write(buffer);
                buffer.clear();
            }

            buffer.put("我收到了路飞的照片".getBytes());
            buffer.flip();
            socketChannel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BlockingClient() throws IOException {
        try (var socketChannel = SocketChannel.open(new InetSocketAddress(
                InetAddress.getLocalHost(), 8000));
             var finChannel = FileChannel.open(Paths.get("Luffy.jpg"),
                     StandardOpenOption.READ)) {
            var buffer = ByteBuffer.allocate(1024);
            while (-1 != finChannel.read(buffer)) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
            socketChannel.shutdownOutput();

            for (var length = 0; -1 != (length = socketChannel.read(buffer)); ) {
                System.out.print(new String(buffer.array(), 0, length));
            }
            System.out.println();
        }
    }

    @Test
    public void NonBlockingTCPServer() {
        try (var serverSocketChannel = (ServerSocketChannel) ServerSocketChannel
                .open().bind(new InetSocketAddress(8000)).configureBlocking(false);
             var selector = Selector.open()) {
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (selector.select() > 0) {
                for (var keyIter = selector.selectedKeys().iterator();
                     keyIter.hasNext(); keyIter.remove()) {
                    var key = keyIter.next();
                    if (key.isAcceptable()) {
                        var socketChannel = serverSocketChannel.accept()
                                .configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        try (var socketChannel = (SocketChannel) key.channel()) {
                            var buffer = ByteBuffer.allocate(1024);
                            var bytesOut = new ByteArrayOutputStream();
                            for (var length = 0; -1 != (length = socketChannel.read(buffer)); ) {
                                buffer.flip();
                                bytesOut.write(buffer.array(), 0, length);
                                buffer.clear();
                            }
                            System.out.println(bytesOut.toString());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void NonBlockingTCPClient() {
        try (var socketChannel = ((SocketChannel) SocketChannel
                .open(new InetSocketAddress(InetAddress.getLocalHost(), 8000))
                .configureBlocking(false));
             var finChannel = FileChannel.open(Paths.get("dbcp.txt"),
                     StandardOpenOption.READ)) {
            for (var buffer = ByteBuffer.allocate(1024); -1 != finChannel.read(buffer);
                 buffer.clear()) {
                buffer.flip();
                socketChannel.write(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void send() {
        try (var datagramChannel = (DatagramChannel) DatagramChannel.open()
                .configureBlocking(false);
             var finChannel = FileChannel.open(Paths.get("dbcp.txt"),
                     StandardOpenOption.READ)) {
            var buffer = ByteBuffer.allocate(1024);
            for (var address = new InetSocketAddress(InetAddress.getLocalHost(), 8900);
                 -1 != finChannel.read(buffer); buffer.clear()) {
                buffer.flip();
                datagramChannel.send(buffer, address);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void receive() {
        try (var datagramChannel = (DatagramChannel) DatagramChannel.open()
                .bind(new InetSocketAddress(InetAddress.getLocalHost(), 8900))
                .configureBlocking(false);
             var selector = Selector.open()) {
            datagramChannel.register(selector, SelectionKey.OP_READ);
            while (selector.select() > 0) {
                for (var keyIter = selector.selectedKeys().iterator();
                     keyIter.hasNext(); keyIter.remove()) {
                    var key = keyIter.next();
                    if (key.isReadable()) {
                        var buffer = ByteBuffer.allocate(1024);
                        datagramChannel.receive(buffer);
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, buffer.limit()));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
