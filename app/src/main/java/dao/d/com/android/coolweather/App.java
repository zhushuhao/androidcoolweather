package dao.d.com.android.coolweather;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static App sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static App getAppContext() {
        if (sContext == null) {
            synchronized (App.class) {
                if (sContext == null) {
                    sContext = new App();
                }
            }
        }
        return sContext;
    }

}
