package a.myweatherapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import a.myweatherapp.adapters.ItemsListAdapter;
import a.myweatherapp.model.AppDatabase;
import a.myweatherapp.model.RoomItem;
import a.myweatherapp.support.AndroidResolver;

public class WeatherActivity extends AppCompatActivity implements IView {

    private IPresenter presenter;
    private ItemsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        AppDatabase db = AppDatabase.getDatabase(this);
        MyLocationManager locationManager = new MyLocationManager(this);

        presenter = new Presenter(db, new AndroidResolver(this), locationManager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.refreshWeatherData();
                Snackbar.make(view, "Refresh weather", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        adapter = new ItemsListAdapter();
        RecyclerView recyclerView = findViewById(R.id.rvItemsList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        presenter.attachView(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        getApplicationContext().startActivity(
                                new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                        dialog.cancel();
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.refreshWeatherData();
    }

    @Override
    public void showItems(List<RoomItem> items) {
        if (items != null)
            adapter.addItems(items);
    }

    @Override
    public void showNetworkErrorToast() {
        Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showCommonErrorToast(String log) {
        Toast.makeText(this, "Error: " + log, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        presenter.detachView();
        super.onDestroy();
    }
}
