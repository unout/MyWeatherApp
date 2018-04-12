package a.myweatherapp.model;

import com.google.gson.annotations.SerializedName;

class Wind {

    @SerializedName("speed")
    private double speed;

    @SerializedName("deg")
    private double degree;

    public int getSpeed() {
        return (int) speed;
    }

    public int getDegree() {
        return (int) degree;
    }
}
