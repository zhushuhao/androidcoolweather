package dao.d.com.android.coolweather.bean.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HeWeather implements Serializable {

    @SerializedName("HeWeather")
    private List<Weather> weather;

    public List<Weather> getWeather() {
        if (weather == null) {
            weather = new ArrayList<>();
        }
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }
}
