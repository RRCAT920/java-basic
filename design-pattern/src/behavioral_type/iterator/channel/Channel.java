package behavioral_type.iterator.channel;

/**
 * @author huzihao
 * @since 2020/11/10 12:37
 */
public class Channel {
    private double frequency; // 频道
    private ChannelLang lang;

    public Channel(double frequency, ChannelLang lang) {
        this.frequency = frequency;
        this.lang = lang;
    }

    public double getFrequency() {
        return frequency;
    }

    public ChannelLang getLang() {
        return lang;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "frequency=" + frequency +
                ", lang=" + lang +
                '}';
    }
}
