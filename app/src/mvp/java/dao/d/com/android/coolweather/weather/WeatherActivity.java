package dao.d.com.android.coolweather.weather;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import dao.d.com.android.coolweather.R;
import dao.d.com.android.coolweather.area.AreaManager;
import dao.d.com.android.coolweather.bean.weather.Forecast;
import dao.d.com.android.coolweather.bean.weather.HeWeather;
import dao.d.com.android.coolweather.bean.weather.Weather;
import dao.d.com.android.coolweather.main.MainActivity;
import dao.d.com.android.coolweather.utils.StatusBarUtil;

public class WeatherActivity extends RxAppCompatActivity implements WeatherContract.View {

    private View view;
    private ImageView ivBg;

    private TextView tvTitle;
    private ImageView ivHome;
    private TextView tvUpTime;

    private TextView tvDegree;
    private TextView tvWeatherInfo;

    private LinearLayout llForecast;

    private TextView tvAqi;
    private TextView tvPM25;

    private TextView tvComfort, tvCarWash, tvSport;

    public DrawerLayout drawerLayout;

    public SwipeRefreshLayout swipeRefreshLayout;

    private WeatherContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0 全透明实现
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);//
            //StatusBarUtil.calculateStatusColor(Color.WHITE, 0)
            //window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(Color.BLACK);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4 全透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        setContentView(R.layout.activity_weather);

        Log.e("onCreate", "WeatherActivity");

        view = findViewById(R.id.view);

        view.getLayoutParams().height = StatusBarUtil.getStatusBarHeight(this);

        Log.e("height", "" + view.getLayoutParams().height);
        ivBg = findViewById(R.id.iv_bg);

        tvTitle = findViewById(R.id.tv_title);
        ivHome = findViewById(R.id.iv_home);
        tvUpTime = findViewById(R.id.tv_update_time);

        tvDegree = findViewById(R.id.tv_degree);
        tvWeatherInfo = findViewById(R.id.tv_weather_info);

        llForecast = findViewById(R.id.ll_forecast);

        tvAqi = findViewById(R.id.tv_aqi);
        tvPM25 = findViewById(R.id.tv_pm_25);

        tvComfort = findViewById(R.id.tv_comfort);
        tvCarWash = findViewById(R.id.tv_car_wash);
        tvSport = findViewById(R.id.tv_sport);

        drawerLayout = findViewById(R.id.drawer);

        swipeRefreshLayout = findViewById(R.id.swipeRefresh);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadWeather();
            }
        });

        presenter = new WeatherPresenter(this, this);

        ivHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


    }


    private boolean isFirstIn = true;

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirstIn) {
            isFirstIn = false;
            loadWeather();
            loadBgImageUrl();

        }
    }

    /**
     * 加载背景图
     */
    private void loadBgImageUrl() {
        presenter.loadBgImageUrl();
    }


    /**
     * 加载天气
     */
    public void loadWeather() {
        swipeRefreshLayout.setRefreshing(true);
        presenter.loadWeather(AreaManager.getInstance().getCurrentCountry().getWeatherId(), MainActivity.KEY);
    }

    private Weather weather;

    /**
     * 加载成功
     */
    @Override
    public void onLoadWeatherSuccess(HeWeather heWeather) {
        swipeRefreshLayout.setRefreshing(false);
        if (heWeather.getWeather().size() > 0) {
            weather = heWeather.getWeather().get(0);
            updateWeatherUI();
        }

    }

    /**
     * 更新UI
     */
    private void updateWeatherUI() {
        tvTitle.setText(weather.getBasic().getCityName());
        tvUpTime.setText(weather.getBasic().getUpdate().getUpdateTime().split(" ")[1]);
        tvDegree.setText(weather.getNow().getTemperature() + "℃");
        tvWeatherInfo.setText(weather.getNow().getMore().getInfo());
        llForecast.removeAllViews();

        for (Forecast forecast : weather.getForecastList()) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_forecast, llForecast, false);
            TextView tvDate = view.findViewById(R.id.tv_date);
            TextView tvInfo = view.findViewById(R.id.tv_info);
            TextView tvMax = view.findViewById(R.id.tv_max);
            TextView tvMin = view.findViewById(R.id.tv_min);

            tvDate.setText(forecast.getDate());
            tvInfo.setText(forecast.getMore().getInfo());
            tvMax.setText(forecast.getTemperature().getMax());
            tvMin.setText(forecast.getTemperature().getMin());
            llForecast.addView(view);
        }

        tvAqi.setText(weather.getAqi().getCity().getAqi());
        tvPM25.setText(weather.getAqi().getCity().getPm25());
        tvComfort.setText("舒适度：" + weather.getSuggestion().getComfort().getInfo());
        tvCarWash.setText("洗车指数：" + weather.getSuggestion().getCarWash().getInfo());
        tvSport.setText("运动建议：" + weather.getSuggestion().getSport().getInfo());
    }

    /**
     * 加载失败
     *
     * @param s
     */
    @Override
    public void onLoadWeatherFailure(String s) {
        swipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 获取背景图片成功
     *
     * @param url
     */
    @Override
    public void onGetBackgroundImageSuccess(String url) {
        Glide.with(this).load(url).into(ivBg);
    }

    /**
     * 获取背景图片失败
     *
     * @param s
     */
    @Override
    public void onGetBackgroundImageFailure(String s) {

    }
}
