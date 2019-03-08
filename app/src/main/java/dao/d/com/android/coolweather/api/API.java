package dao.d.com.android.coolweather.api;

import java.util.HashMap;

import io.reactivex.Flowable;

public class API {

    /**
     * 登录接口
     *
     * @return
     */

    public static Flowable<Void> login() {
        return RetrofitManager.getSingleton().getApiService().login(new HashMap<String, String>());
    }
}
