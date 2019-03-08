package dao.d.com.android.coolweather.weather;


import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dao.d.com.android.coolweather.BaseSubscriber;
import dao.d.com.android.coolweather.api.BaseSchedulers;
import dao.d.com.android.coolweather.api.ExceptionHandle;
import dao.d.com.android.coolweather.bean.weather.HeWeather;

public class WeatherPresenter extends WeatherContract.Presenter {

    private WeatherContract.View mView;
    private WeatherContract.Model mModel;

    private WeatherPresenter() {
    }

    public WeatherPresenter(RxAppCompatActivity activity, WeatherContract.View view) {
        mActivity = activity;
        mView = view;
        mModel = new WeatherModel();
    }

    /**
     * 加载天气
     *
     * @param weatherId
     * @param key
     * @return
     */
    @Override
    void loadWeather(String weatherId, String key) {
        mModel.loadWeather(weatherId, key)
                .compose(BaseSchedulers.flowableCompose())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseSubscriber<HeWeather>() {
                    @Override
                    public void onSuccess(HeWeather heWeather) {
                        mView.onLoadWeatherSuccess(heWeather);
                    }

                    @Override
                    public void onFail(ExceptionHandle.ResponseThrowable e) {
                        mView.onLoadWeatherFailure(e.message);
                    }
                });
    }

    /**
     * 加载背景图
     *
     * @return
     */
    @Override
    void loadBgImageUrl() {
        mModel.loadBgImageUrl()
                .compose(BaseSchedulers.flowableCompose())
                .compose(mActivity.bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(new BaseSubscriber<String>() {
                    @Override
                    public void onSuccess(String s) {
                        mView.onGetBackgroundImageSuccess(s);
                    }

                    @Override
                    public void onFail(ExceptionHandle.ResponseThrowable e) {
                        mView.onGetBackgroundImageFailure(e.message);
                    }
                });
    }
}
