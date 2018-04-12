package a.myweatherapp.model;

import com.google.gson.annotations.SerializedName;

class MainParameters {

    private static final int TO_MM_HG = 133;
    private static final int TO_PASCALS = 100;
    @SerializedName("temp")
    private double temp;

    @SerializedName("pressure")
    private double pressure;

    @SerializedName("humidity")
    private double humidity;

    @SerializedName("temp_min")
    private double tempMin;

    @SerializedName("temp_max")
    private double tempMax;

    public int getTemp() {
        return (int) temp;
    }

    public int getPressure() {
        return (int) (pressure * TO_PASCALS / TO_MM_HG);
    }

    public int getHumidity() {
        return (int) humidity;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }
}
