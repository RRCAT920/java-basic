package behavioral_type.iterator.channel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/10 12:44
 */
public class ChannelList implements ChannelCollection {
    private List<Channel> channelList = new ArrayList<>();

    @Override
    public void add(Channel channel) {
        channelList.add(channel);
    }

    @Override
    public void remove(Channel channel) {
        channelList.remove(channel);
    }

    @Override
    public ChannelIterator iterator() {
        return new Itr();
    }

    private class Itr implements ChannelIterator {
        int cursor;       // index of next element to return

        @Override
        public boolean hasNext() {
            return cursor != channelList.size();
        }

        @Override
        public Channel next() {
            return channelList.get(cursor++);
        }
    }
}
