package a.myweatherapp.adapters.viewholders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a.myweatherapp.R;
import a.myweatherapp.model.RoomItem;
import a.myweatherapp.support.Constants;

public class ItemViewHolder extends com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder {

    private static final String CELSIUS_SIGN = " \u2103";
    private static final String PERCENT_SIGN = " %";
    private static final String SPEED_IN_M_S = " m/s";
    private static final String PRESSURE_IN_MM_HG = " mm Hg";

    private TextView date;
    private TextView main;
    private TextView city;
    private TextView wind;
    private TextView temperature;
    private TextView humidity;
    private TextView pressure;
    private TextView day;
    private TextView time;
    private ImageView icon;

    public ItemViewHolder(View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.tvDate);
        main = itemView.findViewById(R.id.tvMain);
        day = itemView.findViewById(R.id.tvDay);
        time = itemView.findViewById(R.id.tvTime);
        city = itemView.findViewById(R.id.tvCity);
        temperature = itemView.findViewById(R.id.tvTemp);
        wind = itemView.findViewById(R.id.tvWind);
        humidity = itemView.findViewById(R.id.tvHumidity);
        pressure = itemView.findViewById(R.id.tvPressure);
        icon = itemView.findViewById(R.id.ivIcon);
    }

    public void setHolder(RoomItem item) {

        date.setText(item.getDate());
        main.setText(item.getDescription());
        day.setText(item.getDay());
        time.setText(item.getTime());

        city.setText(item.getCity());

        String temp = Integer.toString(item.getTemp()) + CELSIUS_SIGN;
        temperature.setText(temp);

        String hum = Integer.toString(item.getHumidity()) + PERCENT_SIGN;
        humidity.setText(hum);

        String windSpeed = Integer.toString(item.getWindSpeed()) + SPEED_IN_M_S;
        wind.setText(windSpeed);

        String press = Integer.toString(item.getPressure()) + PRESSURE_IN_MM_HG;
        pressure.setText(press);

        String url = Constants.ICON_URL + item.getIcon() + ".png";
        Glide.with(this.itemView.getContext())
                .load(url)
                .into(icon);
    }
}