package behavioral_type.observer;

/**
 * 所有的观察者都要实现此接口，这个接口只有一个update方法，当主题状态改变时被调用
 *
 * @author huzihao
 * @since 2020/11/3 21:40
 */
public interface Observer {
    /**
     * 当目标的状态改变时，它会将这些参数的值传递给观察者
     *
     * @param temp     温度
     * @param humidity 湿度
     * @param pressure 气压
     */
    void update(float temp, float humidity, float pressure);
}
