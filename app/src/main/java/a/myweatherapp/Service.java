package a.myweatherapp;


import a.myweatherapp.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("/data/2.5/weather?units=metric")
    Call<WeatherResponse> getWeather(@Query("lat") String lat, @Query("lon") String lon, @Query("APPID") String appid);
}

