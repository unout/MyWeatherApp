package a.myweatherapp;


import java.util.List;

import a.myweatherapp.model.AppDatabase;
import a.myweatherapp.model.RoomItem;
import a.myweatherapp.model.WeatherResponse;
import retrofit2.Response;

public class Model implements IModel {

    private AppDatabase db;
    private Presenter.Resolver resolver;
    private MyLocationManager locationManager;

    public Model(AppDatabase db, Presenter.Resolver resolver, MyLocationManager locationManager) {
        this.db = db;
        this.resolver = resolver;
        this.locationManager = locationManager;
    }

    @Override
    public List<RoomItem> getItems() {
        return db.itemDAO().getAll();
    }

    @Override
    public void saveWeather(Response<WeatherResponse> response, String[] location) {
        WeatherResponse weatherResponse = response.body();
        if (weatherResponse != null) {
            db.itemDAO().insert(new RoomItem(
                    weatherResponse.getWeatherElements().get(0),
                    weatherResponse.getMainParameters(),
                    weatherResponse.getWind(),
                    weatherResponse.getCity(),
                    location));
        }
    }

    @Override
    public boolean isOnline() {
        return resolver.isNetworkAvailable();
    }

    @Override
    public String[] getLocation() {
        return locationManager.getLocation();
    }
}
