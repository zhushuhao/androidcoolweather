package dao.d.com.android.coolweather.bean.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Forecast implements Serializable {
    private String date;
    @SerializedName("tmp")
    private Temperature temperature;
    @SerializedName("cond")
    private More more;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Temperature getTemperature() {
        if (temperature == null) {
            temperature = new Temperature();
        }
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public More getMore() {
        if (more == null) {
            more = new More();
        }
        return more;
    }

    public void setMore(More more) {
        this.more = more;
    }

    public class Temperature implements Serializable {
        private String max;
        private String min;

        public String getMax() {
            return max;
        }

        public void setMax(String max) {
            this.max = max;
        }

        public String getMin() {
            return min;
        }

        public void setMin(String min) {
            this.min = min;
        }
    }


    public class More implements Serializable {
        @SerializedName("txt_d")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


}
