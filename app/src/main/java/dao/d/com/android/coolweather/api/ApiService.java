package dao.d.com.android.coolweather.api;

import java.util.List;
import java.util.Map;

import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;
import dao.d.com.android.coolweather.bean.weather.HeWeather;
import io.reactivex.Flowable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    /**
     * 登录
     * post
     * 表单提交
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("login")
    Flowable<Void> login(@FieldMap Map<String, String> map);


    @GET("api/china")
    Flowable<List<Province>> getProvinces();

    @GET("api/china/{provinceId}")
    Flowable<List<City>> getCities(@Path("provinceId") int provinceId);


    @GET("api/china/{provinceId}/{cityId}")
    Flowable<List<Country>> getCounties(@Path("provinceId") int provinceId, @Path("cityId") int cityId);


    @GET("api/weather")
    Flowable<HeWeather> getWeather(@Query("cityid") String weatherId, @Query("key") String key);

    @GET("api/bing_pic")
    Flowable<String> getBingPck();


}
