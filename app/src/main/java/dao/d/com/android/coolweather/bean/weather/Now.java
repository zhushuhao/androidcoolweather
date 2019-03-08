package dao.d.com.android.coolweather.bean.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Now implements Serializable {
    @SerializedName("tmp")
    private String temperature;

    @SerializedName("cond")
    private More more;

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
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

    public class More implements Serializable {
        @SerializedName("txt")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


}
