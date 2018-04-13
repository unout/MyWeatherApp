package a.myweatherapp.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static a.myweatherapp.MyLocationManager.DATE;
import static a.myweatherapp.MyLocationManager.DAY;
import static a.myweatherapp.MyLocationManager.LATITUDE;
import static a.myweatherapp.MyLocationManager.LONGITUDE;
import static a.myweatherapp.MyLocationManager.TIME;

@Entity(tableName = "weatherdb")
public class RoomItem {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "city")
    private String city;

    @ColumnInfo(name = "latitude")
    private String latitude;

    @ColumnInfo(name = "longitude")
    private String longitude;

    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "icon")
    private String icon;

    @ColumnInfo(name = "temp")
    private int temp;

    @ColumnInfo(name = "pressure")
    private int pressure;

    @ColumnInfo(name = "humidity")
    private int humidity;

    @ColumnInfo(name = "windSpeed")
    private int windSpeed;

    @ColumnInfo(name = "windDir")
    private int windDir;

    private String day;

    private String time;

    public RoomItem() {}

    public RoomItem(WeatherElement main, MainParameters params, Wind wind, String city, String[] location) {
        this.city = city;
        this.icon = main.getIcon();
        this.description = main.getDescription();
        this.temp = params.getTemp();
        this.pressure = params.getPressure();
        this.humidity = params.getHumidity();
        this.windSpeed = wind.getSpeed();
        this.windDir = wind.getDegree();
        this.longitude = location[LONGITUDE];
        this.latitude = location[LATITUDE];
        this.date = location[DATE];
        this.day = location[DAY];
        this.time = location[TIME];
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public int getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public int getWindDir() {
        return windDir;
    }

    public String getDay() {
        return day;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(int temp) {
        this.temp = temp;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public void setWindDir(int windDir) {
        this.windDir = windDir;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}