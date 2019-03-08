package dao.d.com.android.coolweather.area;

import java.util.List;

import dao.d.com.android.coolweather.api.RetrofitManager;
import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;
import io.reactivex.Flowable;

public class AreaModel implements AreaContract.Model {

    /**
     * 加载省份
     */
    @Override
    public Flowable<List<Province>> loadProvince() {
        return RetrofitManager.getSingleton().getApiService().getProvinces();
    }

    /**
     * 加载城市
     *
     * @param provinceId
     */
    @Override
    public Flowable<List<City>> loadCity(int provinceId) {
        return RetrofitManager.getSingleton().getApiService().getCities(provinceId);
    }

    /**
     * 加载县
     *
     * @param provinceId
     * @param cityId
     */
    @Override
    public Flowable<List<Country>> loadCountry(int provinceId, int cityId) {
        return RetrofitManager.getSingleton().getApiService().getCounties(provinceId, cityId);
    }
}
