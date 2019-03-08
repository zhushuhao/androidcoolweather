package dao.d.com.android.coolweather.utils;

import android.widget.Toast;

import dao.d.com.android.coolweather.App;

public class ToastUtil {
    public static void showToast(String content){
        Toast.makeText(App.getAppContext(),content,Toast.LENGTH_SHORT).show();
    }
}
