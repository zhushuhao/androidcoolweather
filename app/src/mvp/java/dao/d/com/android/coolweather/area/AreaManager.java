package dao.d.com.android.coolweather.area;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;

public class AreaManager {


    private static final int LEVEL_PROVINCE = 0;
    private static final int LEVEL_CITY = 1;
    private static final int LEVEL_COUNTRY = 2;

    private List<Province> provinceList = new ArrayList<>();
    private List<City> cityList = new ArrayList<>();
    private List<Country> countryList = new ArrayList<>();

    private int level;
    private static AreaManager instance;

    private Province currentProvince;
    private City currentCity;
    private Country currentCountry;

    private Province tempSelectedProvince;
    private City tempSelectedCity;

    public Province getTempSelectedProvince() {
        return tempSelectedProvince;
    }

    public void setTempSelectedProvince(Province province) {
        this.tempSelectedProvince = province;
        Log.e("tempSelectedProvince", "" + tempSelectedProvince);
        Log.e("province", "" + province);
    }

    public City getTempSelectedCity() {
        return tempSelectedCity;
    }

    public void setTempSelectedCity(City city) {
        this.tempSelectedCity = city;
    }

    public List<Province> getProvinceList() {
        return provinceList;
    }

    public void clearThenAddAllProvince(List<Province> list) {
        provinceList.clear();
        provinceList.addAll(list);
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void clearThenAddAllCity(List<City> list) {
        cityList.clear();
        cityList.addAll(list);
    }


    public List<Country> getCountryList() {
        return countryList;
    }

    public void clearThenAddAllCountry(List<Country> list) {
        countryList.clear();
        countryList.addAll(list);
    }

    public Province getCurrentProvince() {
        return currentProvince;
    }

    public void setCurrentProvince(Province currentProvince) {
        this.currentProvince = currentProvince;
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City currentCity) {
        this.currentCity = currentCity;
    }

    public Country getCurrentCountry() {
        return currentCountry;
    }

    public void setCurrentCountry(Country currentCountry) {
        this.currentCountry = currentCountry;
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
