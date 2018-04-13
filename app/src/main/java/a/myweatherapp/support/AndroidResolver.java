package a.myweatherapp.support;


import android.content.Context;

import java.lang.ref.WeakReference;

import a.myweatherapp.Presenter;

public class AndroidResolver implements Presenter.Resolver {

    private final WeakReference<Context> contextWeakReference;

    public AndroidResolver(Context context) {
        this.contextWeakReference = new WeakReference<>(context);
    }

    @Override
    public boolean isNetworkAvailable() {
        return !NetworkUtils.isNetworkEnabled(contextWeakReference.get());
    }
}
