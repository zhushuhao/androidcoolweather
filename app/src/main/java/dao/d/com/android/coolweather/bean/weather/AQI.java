package dao.d.com.android.coolweather.bean.weather;

import java.io.Serializable;

public class AQI implements Serializable {

    private AQICity city;

    public AQICity getCity() {
        if (city == null) {
            city = new AQICity();
        }
        return city;
    }

    public void setCity(AQICity city) {
        this.city = city;
    }

    public class AQICity implements Serializable {
        private String aqi;
        private String pm25;

        public String getAqi() {
            return aqi;
        }

        public void setAqi(String aqi) {
            this.aqi = aqi;
        }

        public String getPm25() {
            return pm25;
        }

        public void setPm25(String pm25) {
            this.pm25 = pm25;
        }
    }

}
