package a.myweatherapp;

public interface IPresenter {

    void attachView(IView view);

    void detachView();

    void refreshWeatherData();
}