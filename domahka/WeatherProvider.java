package lesson7.domahka;

import java.io.IOException;

public interface WeatherProvider {
    public void getWeather(Periods period) throws IOException;
}
