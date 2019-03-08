package dao.d.com.android.coolweather.area;

import java.util.List;

import dao.d.com.android.coolweather.BasePresenter;
import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;
import io.reactivex.Flowable;

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
         */
        Flowable<List<Province>> loadProvince();

        /**
         * 加载城市
         *
         * @param provinceId
         */
        Flowable<List<City>> loadCity(int provinceId);

        /**
         * 加载县
         *
         * @param provinceId
         * @param cityId
         */
        Flowable<List<Country>> loadCountry(int provinceId, int cityId);

    }

    abstract class Presenter extends BasePresenter {
        /**
         * 加载省份
         */
        abstract void loadProvince();

        /**
         * 加载城市
         *
         * @param provinceId
         */
        abstract void loadCity(int provinceId);

        /**
         * 加载县
         *
         * @param provinceId
         * @param cityId
         */
        abstract void loadCountry(int provinceId, int cityId);

    }
}
