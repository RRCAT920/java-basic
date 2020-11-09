package behavioral_type.observer.weather_station;

/**
 * 主题接口，对象使用此接口注册为观察者，或者把自己从观察者中删除。每个主题都可以有很多观察者
 *
 * @author huzihao
 * @since 2020/11/3 21:39
 */
public interface Subject {
    /**
     * 注册观察者
     *
     * @param o 要注册的观察者
     */
    void registerObserver(Observer o);

    /**
     * 移除观察者
     *
     * @param o 要移除的观察者
     */
    void removeObserver(Observer o);

    /**
     * 当主题状态改变时，调用此方法，通知所有观察者
     */
    void notifyObservers();
}
