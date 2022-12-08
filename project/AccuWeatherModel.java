package lesson7.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class AccuWeatherModel implements WeatherModel {
//  http://dataservice.accuweather.com/forecasts/v1/daily/1day/

    private static final String PROTOCOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAYS = "1day";
    private static final String API_KEY = "uRU1AhYfaDTChyAMMd7mf4GdoUrphDgk";
    private static final String API_KEY_QUERY_PARAM = "25123";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();




    @Override
    public void getWeather(String selectedCity, Period period) throws IOException {
        switch (period){
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOCOL)
                        .host(BASE_HOST)
                        .addPathSegments(FORECASTS)
                        .addPathSegments(VERSION)
                        .addPathSegments(DAILY)
                        .addPathSegments(ONE_DAYS)
                        .addPathSegments(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();


                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();
                String weatherResponse = oneDayForecastResponse.body().string();
                System.out.println(weatherResponse);

                break;


            case FIVE_DAYS:


                break;

        }

    }

    private String detectCityKey(String selectedCity) throws IOException {
//        http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOCOL)
                .host(BASE_HOST)
                .addPathSegments(LOCATIONS)
                .addPathSegments(CITIES)
                .addPathSegments(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectedCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response sityResponse = okHttpClient.newCall(request).execute();
        String weatherResponse = sityResponse.body().string();

        String cityKey = objectMapper.readTree(weatherResponse).get(0).at("/Key").asText();


        return cityKey;
    }

    public static void main(String[] args) throws IOException {
       UserinterfaceView userinterfaceView = new UserinterfaceView();
       userinterfaceView.raunInterface();

    }
}
