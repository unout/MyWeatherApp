package a.myweatherapp;

import java.util.List;

import a.myweatherapp.model.RoomItem;
import a.myweatherapp.model.WeatherResponse;
import retrofit2.Response;

public interface IModel {

    List<RoomItem> getItems();

    void saveWeather(Response<WeatherResponse> response, String[] location);

    boolean isOnline();

    String[] getLocation();
}