package dao.d.com.android.coolweather.area;


import com.trello.rxlifecycle2.android.FragmentEvent;
import com.trello.rxlifecycle2.components.RxFragment;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.List;

import dao.d.com.android.coolweather.BaseSubscriber;
import dao.d.com.android.coolweather.api.BaseSchedulers;
import dao.d.com.android.coolweather.api.ExceptionHandle;
import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AreaPresenter extends AreaContract.Presenter {

    private AreaContract.View mView;
    private RxAppCompatActivity mActivity;
    private RxFragment mFragment;
    private AreaContract.Model mModel;

    private AreaPresenter() {
    }

    public AreaPresenter(RxAppCompatActivity activity, AreaContract.View view) {
        mActivity = activity;
        mView = view;
        mModel = new AreaModel();
    }

    public AreaPresenter(RxFragment fragment, AreaContract.View view) {
        mView = view;
        mFragment = fragment;
        mModel = new AreaModel();
    }

    /**
     * 加载省份
     */
    @Override
    void loadProvince() {

        mModel.loadProvince()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseSubscriber<List<Province>>() {
                    @Override
                    public void onSuccess(List<Province> provinces) {

                        mView.onLoadProvinceSuccess(provinces);
                    }

                    @Override
                    public void onFail(ExceptionHandle.ResponseThrowable e) {
                        mView.onLoadProvinceFailure(e.message);
                    }
                });
    }

    /**
     * 加载城市
     *
     * @param provinceId
     */
    @Override
    void loadCity(int provinceId) {
        mModel.loadCity(provinceId)
                .compose(BaseSchedulers.flowableCompose())
                .compose(mFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseSubscriber<List<City>>() {
                    @Override
                    public void onSuccess(List<City> cities) {
                        mView.onLoadCitySuccess(cities);
                    }

                    @Override
                    public void onFail(ExceptionHandle.ResponseThrowable e) {
                        mView.onLoadCityFailure(e.message);
                    }
                });
    }

    /**
     * 加载县
     *
     * @param provinceId
     * @param cityId
     */
    @Override
    void loadCountry(int provinceId, int cityId) {
        mModel.loadCountry(provinceId, cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mFragment.bindUntilEvent(FragmentEvent.DESTROY))
                .subscribe(new BaseSubscriber<List<Country>>() {
                    @Override
                    public void onSuccess(List<Country> countries) {
                        mView.onLoadCountrySuccess(countries);
                    }

                    @Override
                    public void onFail(ExceptionHandle.ResponseThrowable e) {
                        mView.onLoadCountryFailure(e.message);
                    }
                });
    }


}
