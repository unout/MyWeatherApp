package a.myweatherapp.model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("weather")
    private List<WeatherElement> weatherElements;

    @SerializedName("main")
    private MainParameters mainParameters;

    @SerializedName("wind")
    private Wind wind;

    @SerializedName("name")
    private String city;

    @NonNull
    public List<WeatherElement> getWeatherElements() {
        return weatherElements;
    }

    @NonNull
    public MainParameters getMainParameters() {
        return mainParameters;
    }

    @NonNull
    public Wind getWind() {
        return wind;
    }

    public String getCity() {
        return city;
    }

    /*
     "coord": {
        "lon": 27.56,
        "lat": 53.9
    },
    "weather": [
        {
            "id": 800,
            "main": "Clear",
            "description": "clear sky",
            "icon": "01d"
        }
    ],
    "base": "stations",
    "main": {
        "temp": 284.69,
        "pressure": 1018,
        "humidity": 62,
        "temp_min": 284.15,
        "temp_max": 285.15
    },
    "visibility": 10000,
    "wind": {
        "speed": 4,
        "deg": 160
    },
    "clouds": {
        "all": 0
    },
    "dt": 1523257200,
    "sys": {
        "type": 1,
        "id": 7377,
        "message": 0.0024,
        "country": "BY",
        "sunrise": 1523244183,
        "sunset": 1523293240
    },
    "id": 625144,
    "name": "Minsk",
    "cod": 200

     */
}