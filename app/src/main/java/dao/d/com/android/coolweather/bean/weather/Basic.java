package dao.d.com.android.coolweather.bean.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Basic implements Serializable {
    @SerializedName("city")
    private String cityName;
    @SerializedName("id")
    private String weatherId;

    private Update update;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getWeatherId() {
        return weatherId;
    }

    public void setWeatherId(String weatherId) {
        this.weatherId = weatherId;
    }

    public Update getUpdate() {
        if (update == null) {
            update = new Update();
        }
        return update;
    }

    public void setUpdate(Update update) {
        this.update = update;
    }

    public class Update implements Serializable {
        @SerializedName("loc")
        private String updateTime;

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }
    }
}
