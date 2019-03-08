package dao.d.com.android.coolweather.api;

import android.util.Log;

import java.util.concurrent.TimeUnit;

import dao.d.com.android.coolweather.Constants.UrlConstants;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {
    private volatile static RetrofitManager singleton;
    private Retrofit retrofit;
    private OkHttpClient okHttpClient;

    private RetrofitManager() {
    }

    public static RetrofitManager getSingleton() {
        if (singleton == null) {
            synchronized (RetrofitManager.class) {
                if (singleton == null) {
                    singleton = new RetrofitManager();
                }
            }
        }
        return singleton;
    }

    public ApiService getApiService() {
        return getRetrofit().create(ApiService.class);
    }


    private Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(UrlConstants.BASE_URL)
                    .client(getOkHttpClient())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())     //解析数据格式
                    .build();
        }
        return retrofit;
    }

    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()

                    .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                        @Override
                        public void log(String message) {   //访问网络请求，和服务端响应请求时。将数据拦截并输出
                            Log.e("getOkHttpClient", "log: " + message);
                        }
                    }).setLevel(HttpLoggingInterceptor.Level.BODY))     //Log等级
                    .connectTimeout(10, TimeUnit.SECONDS)       //超时时间
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .build();
        }
        return okHttpClient;
    }
}
