package behavioral_type.iterator.channel;

/**
 * @author huzihao
 * @since 2020/11/10 12:42
 */
public interface ChannelCollection {
    void add(Channel channel);

    void remove(Channel channel);

    ChannelIterator iterator();
}
