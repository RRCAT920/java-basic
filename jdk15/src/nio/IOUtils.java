package nio;

import java.nio.ByteBuffer;

/**
 * @author huzihao
 * @since 2020/10/3 13:36
 */
public final class IOUtils {

    public static ByteBuffer[] getByteBuffers(int... capacity) {
        var buffers = new ByteBuffer[capacity.length];
        for (var i = 0; i < capacity.length; i++) {
            buffers[i] = ByteBuffer.allocate(capacity[i]);
        }
        return buffers;
    }
}
