package dao.d.com.android.coolweather.bean.place;

import com.google.gson.annotations.SerializedName;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

public class Country extends LitePalSupport implements Serializable {
    @SerializedName("name")
    private String countyName;
    @SerializedName("weather_id")
    private String weatherId;

    private transient int id = 0;

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
