package dao.d.com.android.coolweather.bean.place;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {
    @SerializedName("name")
    private String countyName;
    @SerializedName("weather_id")
    private int weatherId;
    private int cityId;

    private transient int id = 0;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public int getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(int weatherId) {
        this.weatherId = weatherId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
