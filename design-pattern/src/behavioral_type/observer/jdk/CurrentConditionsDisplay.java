package behavioral_type.observer.jdk;

import java.util.Observable;
import java.util.Observer;

import behavioral_type.observer.DisplayElement;

/**
 * @author huzihao
 * @since 2020/11/3 22:12
 */
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private final Observable observable;

    public CurrentConditionsDisplay(Observable observable) {
        this.observable = observable;
        observable.addObserver(this);
    }

    @Override
    public void display() {
        System.out.printf("Current conditions: %f F degrees and %f %% humidity", temperature, humidity);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WeatherData weatherData) {
            temperature = weatherData.getTemperature();
            humidity = weatherData.getHumidity();
            display();
        }
    }
}
