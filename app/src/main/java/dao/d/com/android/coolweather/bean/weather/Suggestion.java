package dao.d.com.android.coolweather.bean.weather;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Suggestion implements Serializable {

    @SerializedName("comf")
    private Comfort comfort;
    @SerializedName("cw")
    private CarWash carWash;
    private Sport sport;

    public Comfort getComfort() {
        if (comfort == null) {
            comfort = new Comfort();
        }
        return comfort;
    }

    public void setComfort(Comfort comfort) {
        this.comfort = comfort;
    }

    public CarWash getCarWash() {
        if (carWash == null) {
            carWash = new CarWash();
        }
        return carWash;
    }

    public void setCarWash(CarWash carWash) {
        this.carWash = carWash;
    }

    public Sport getSport() {
        if (sport == null) {
            sport = new Sport();
        }
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    private class Comfort implements Serializable {
        @SerializedName("txt")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }


    private class CarWash implements Serializable {
        @SerializedName("txt")
        private String info;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    private class Sport implements Serializable {
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
