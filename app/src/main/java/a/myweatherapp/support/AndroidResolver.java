package a.myweatherapp.support;


import android.content.Context;

import a.myweatherapp.Presenter;

public class AndroidResolver implements Presenter.Resolver {

    private final Context context;

    public AndroidResolver(Context context) {
        this.context = context;
    }

    @Override
    public boolean isNetworkAvailable() {
        return !NetworkUtils.isOnline(context);
    }
}
