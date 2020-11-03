package behavioral_type.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huzihao
 * @since 2020/11/3 22:01
 */
public class WeatherData implements Subject {
    private final List<Observer> observerList;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observerList = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observerList.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        observerList.forEach(observer -> observer.update(temperature, humidity, pressure));
    }

    public void measurementsChanged() {
        // 当从气象站得到更新数据时，通知观察者
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}
