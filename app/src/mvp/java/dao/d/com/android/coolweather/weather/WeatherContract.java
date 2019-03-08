package dao.d.com.android.coolweather.weather;

import dao.d.com.android.coolweather.BasePresenter;
import dao.d.com.android.coolweather.bean.weather.HeWeather;
import io.reactivex.Flowable;

public interface WeatherContract {

    interface View {


        /**
         * 加载成功
         */
        void onLoadWeatherSuccess(HeWeather weather);

        /**
         * 加载失败
         *
         * @param s
         */
        void onLoadWeatherFailure(String s);

        /**
         * 获取背景图片成功
         *
         * @param url
         */
        void onGetBackgroundImageSuccess(String url);

        /**
         * 获取背景图片失败
         *
         * @param s
         */
        void onGetBackgroundImageFailure(String s);
    }

    interface Model {
        /**
         * 加载天气
         *
         * @param weatherId
         * @param key
         * @return
         */
        Flowable<HeWeather> loadWeather(String weatherId, String key);

        /**
         * 加载背景图
         *
         * @return
         */
        Flowable<String> loadBgImageUrl();

    }

    abstract class Presenter extends BasePresenter {
        /**
         * 加载天气
         *
         * @param weatherId
         * @param key
         * @return
         */
        abstract void loadWeather(String weatherId, String key);

        /**
         * 加载背景图
         *
         * @return
         */
        abstract void loadBgImageUrl();
    }
}
