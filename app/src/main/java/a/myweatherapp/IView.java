package a.myweatherapp;


import java.util.List;

import a.myweatherapp.model.RoomItem;

public interface IView {

    void showItems(List<RoomItem> items);

    void showNetworkError();

    void showCommonError(String log);
}
