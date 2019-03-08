package dao.d.com.android.coolweather.area;

import java.util.HashMap;
import java.util.List;

import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;

public interface AreaContract {
    interface View {
        /**
         * 加载省份成功
         */
        void onLoadProvinceSuccess(List<Province> data);

        /**
         * 加载省份失败
         *
         * @param s
         */
        void onLoadProvinceFailure(String s);

        /**
         * 加载城市成功
         *
         * @param data
         */
        void onLoadCitySuccess(List<City> data);

        /**
         * 加载城市失败
         *
         * @param s
         */
        void onLoadCityFailure(String s);

        /**
         * 加载县成功
         *
         * @param data
         */
        void onLoadCountrySuccess(List<Country> data);

        /**
         * 加载县失败
         *
         * @param s
         */
        void onLoadCountryFailure(String s);
    }

    interface Model {
        /**
         * 加载省份
         *
         * @param map
         */
        void loadProvince(HashMap<String, Object> map);

        /**
         * 加载城市
         *
         * @param map
         */
        void loadCity(HashMap<String, Object> map);

        /**
         * 加载县
         *
         * @param map
         */
        void loadCountry(HashMap<String, Object> map);

    }

    abstract class Presenter {
        /**
         * 加载省份
         *
         * @param map
         */
        abstract void loadProvince(HashMap<String, Object> map);

        /**
         * 加载城市
         *
         * @param map
         */
        abstract void loadCity(HashMap<String, Object> map);

        /**
         * 加载县
         *
         * @param map
         */
        abstract void loadCountry(HashMap<String, Object> map);

    }
}
