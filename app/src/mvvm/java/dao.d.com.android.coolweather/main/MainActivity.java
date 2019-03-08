package dao.d.com.android.coolweather.main;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import dao.d.com.android.coolweather.mvvm.R;

public class MainActivity extends AppCompatActivity {


    // 备用Key，由于每个Key每天只有1000次免费请求，如果已用超的话请换别的Key使用。
    // 9da35b0a6b2c48498ed9e81b9d5206f3
    // 0099dcee07fd488e8b8866f16453fa2e
    private static final String KEY = "45dd25f63300445e967b461d2037e4f9";

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (TextUtils.isEmpty(KEY)) {
            showDialog();
        } else {

        }
    }

    /**
     * 提示框
     */
    private void showDialog() {
        new AlertDialog.Builder(this)
                .setMessage("请先在MainActivity中配置天气API的Key")
                .setCancelable(false)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                }).show();
    }


}
