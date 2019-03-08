package dao.d.com.android.coolweather.area;

import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;

public class AreaManager {


    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTRY = 2;

    private int level;
    private static AreaManager instance;

    private Province province;
    private City city;
    private Country country;


    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    private AreaManager() {
    }

    public static AreaManager getInstance() {
        if (instance == null) {
            synchronized (AreaManager.class) {
                if (instance == null) {
                    instance = new AreaManager();
                }
            }
        }
        return instance;
    }

    public void setStatusToProvince() {
        this.level = LEVEL_PROVINCE;
    }

    public void setStatusToCity() {
        this.level = LEVEL_CITY;

    }

    public void setStatusToCountry() {
        this.level = LEVEL_COUNTRY;
    }


    public boolean isCountry() {
        return level == LEVEL_COUNTRY;
    }

    public boolean isProvince() {
        return level == LEVEL_PROVINCE;
    }

    public boolean isCity() {
        return level == LEVEL_CITY;
    }
}
