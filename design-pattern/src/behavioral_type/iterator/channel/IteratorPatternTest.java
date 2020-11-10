package behavioral_type.iterator.channel;

/**
 * @author huzihao
 * @since 2020/11/10 12:56
 */
public class IteratorPatternTest {
    public static void main(String[] args) {
        var channelCollection = populateChannels();
        for (var iter = channelCollection.iterator();
             iter.hasNext(); ) {
            System.out.println(iter.next());
        }

    }

    private static ChannelCollection populateChannels() {
        ChannelCollection channels = new ChannelList();
        channels.add(new Channel(98.5, ChannelLang.CHINESE));
        channels.add(new Channel(99.5, ChannelLang.FRENCH));
        channels.add(new Channel(100.5, ChannelLang.ENGLISH));
        channels.add(new Channel(101.5, ChannelLang.CHINESE));
        channels.add(new Channel(103.5, ChannelLang.ENGLISH));
        return channels;
    }
}
