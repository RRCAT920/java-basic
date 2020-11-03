package behavioral_type.observer;

/**
 * @author huzihao
 * @since 2020/11/3 22:17
 */
public class WeatherStation {
    public static void main(String[] args) {
        var weatherData = new WeatherData();

        var currentConditionsDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
    }
}
