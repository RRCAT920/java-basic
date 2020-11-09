package behavioral_type.observer.weather_station.jdk;

import java.util.Observable;

/**
 * @author huzihao
 * @since 2020/11/4 17:57
 */
public class WeatherData extends Observable {
    private float temperature;
    private float humidity;
    private float pressure;
    
    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        setChanged();
        notifyObservers();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }
}
