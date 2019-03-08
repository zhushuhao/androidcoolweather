package dao.d.com.android.coolweather.main;

import android.arch.lifecycle.ViewModel;

import dao.d.com.android.coolweather.WeatherRepository;

public class MainViewModel extends ViewModel {
    private MainViewModel() {

    }

    private WeatherRepository weatherRepository;

    public MainViewModel(WeatherRepository repository) {
        weatherRepository = repository;
    }

}
