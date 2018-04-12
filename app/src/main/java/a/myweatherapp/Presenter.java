package a.myweatherapp;


import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Arrays;

import a.myweatherapp.model.AppDatabase;
import a.myweatherapp.model.WeatherResponse;
import a.myweatherapp.support.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static a.myweatherapp.MyLocationManager.LATITUDE;
import static a.myweatherapp.MyLocationManager.LONGITUDE;

public class Presenter implements IPresenter {

    private IView view;
    private IModel model;

    public Presenter(AppDatabase db, Resolver resolver, MyLocationManager locationManager) {
        model = new Model(db, resolver, locationManager);
    }

    @Override
    public void attachView(IView view) {
        this.view = view;
        if (model.getItems() != null && model.getItems().size() > 0)
            view.showItems(model.getItems());
    }

    @Override
    public void detachView() {
        view = null;
    }

    private void loadWeather(final String[] location) {
        if (model.isOnline()) {
            setInitFinished(Constants.CODE_NETWORK_ERROR, null);
        } else {
            Retrofit restAdapter = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            Service service = restAdapter.create(Service.class);
            Call<WeatherResponse> call = service.getWeather(location[LATITUDE], location[LONGITUDE], Constants.APPID);
            call.enqueue(new Callback<WeatherResponse>() {
                @Override
                public void onResponse(@NonNull Call<WeatherResponse> call, @NonNull Response<WeatherResponse> response) {
                    model.saveWeather(response, location);
                    setInitFinished(Constants.CODE_SUCCESS, null);
                    Log.e(Constants.myLogs, response.toString());
                }

                @Override
                public void onFailure(@NonNull Call<WeatherResponse> call, @NonNull Throwable t) {
                    setInitFinished(Constants.CODE_COMMON_ERROR, t.toString());
                    Log.e("FATAL EXCEPTION!CALL:", call.request().toString());
                    Log.e("FATAL EXCEPTION! : ", t.toString());
                }
            });
        }
    }

    private void setInitFinished(int resultCode, String log) {
        if (view != null) {
            if (resultCode == Constants.CODE_SUCCESS) view.showItems(model.getItems());
            if (resultCode == Constants.CODE_NETWORK_ERROR) view.showNetworkErrorToast();
            if (resultCode == Constants.CODE_COMMON_ERROR) view.showCommonErrorToast(log);
        }
    }

    @Override
    public void refreshWeatherData() {
        if (model.getLocation() != null) {
//            String[] loc = model.getLocation();loc[LATITUDE], loc[LONGITUDE], loc[DATE], loc[DAY]
            loadWeather(model.getLocation());
            Log.e(Constants.myLogs, Arrays.toString(model.getLocation()));
        }
    }

    public interface Resolver {
        boolean isNetworkAvailable();
    }
}
