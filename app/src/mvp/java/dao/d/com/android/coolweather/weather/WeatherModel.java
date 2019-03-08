package dao.d.com.android.coolweather.weather;

import dao.d.com.android.coolweather.api.RetrofitManager;
import dao.d.com.android.coolweather.bean.weather.HeWeather;
import io.reactivex.Flowable;

public class WeatherModel implements WeatherContract.Model {
    /**
     * 加载天气
     *
     * @param weatherId
     * @param key
     * @return
     */
    @Override
    public Flowable<HeWeather> loadWeather(String weatherId, String key) {
        return RetrofitManager.getSingleton().getApiService().getWeather(weatherId,key);
    }

    /**
     * 加载背景图
     *
     * @return
     */
    @Override
    public Flowable<String> loadBgImageUrl() {
        return RetrofitManager.getSingleton().getApiService().getBingPck();
    }
}
