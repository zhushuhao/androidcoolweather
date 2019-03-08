package dao.d.com.android.coolweather.area;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.trello.rxlifecycle2.components.RxFragment;

import java.util.ArrayList;
import java.util.List;

import dao.d.com.android.coolweather.R;
import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;
import dao.d.com.android.coolweather.main.MainActivity;
import dao.d.com.android.coolweather.utils.ToastUtil;
import dao.d.com.android.coolweather.weather.WeatherActivity;

public class AreaFragment extends RxFragment implements AreaContract.View {

    private ImageView ivBack;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ProgressDialog progressDialog;
    private TextView tvTitle;

    private List<String> mData = new ArrayList<>();

    private AreaContract.Presenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        tvTitle = view.findViewById(R.id.tv_title);
        lv = view.findViewById(R.id.lv);
        ivBack = view.findViewById(R.id.iv_back);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, mData);
        lv.setAdapter(adapter);
        presenter = new AreaPresenter(this, this);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AreaManager.getInstance().isCountry()) {
                    loadCity(AreaManager.getInstance().getCurrentProvince().getProvinceCode());
                } else if (AreaManager.getInstance().isCity()) {
                    loadProvince();
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (AreaManager.getInstance().isProvince()) {
                    Province province = AreaManager.getInstance().getProvinceList().get(position);
                    AreaManager.getInstance().setTempSelectedProvince(province);
                    loadCity(province.getProvinceCode());
                } else if (AreaManager.getInstance().isCity()) {
                    City city = AreaManager.getInstance().getCityList().get(position);
                    AreaManager.getInstance().setTempSelectedCity(city);
                    loadCountry(AreaManager.getInstance().getCurrentProvince().getProvinceCode(), city.getCityCode());
                } else {
                    Log.e("country", "clickCountry");
                    if (getActivity() instanceof MainActivity) {
                        Log.e("country", "MainActivity");
                        Intent intent = new Intent(getActivity(), WeatherActivity.class);
                        AreaManager.getInstance().setCurrentCountry(AreaManager.getInstance().getCountryList().get(position));
                        startActivity(intent);
                        getActivity().finish();
                    } else if (getActivity() instanceof WeatherActivity) {
                        Log.e("country", "WeatherActivity");
                        WeatherActivity activity = (WeatherActivity) getActivity();
                        AreaManager.getInstance().setCurrentCountry(AreaManager.getInstance().getCountryList().get(position));
                        activity.drawerLayout.closeDrawers();
                        activity.loadWeather();
                    }
                }
            }
        });

        loadProvince();
        return view;
    }


    /**
     * 从市返回时获取省份数据
     */
    private void loadProvince() {
        showProgressDialog();
        presenter.loadProvince();
    }

    /**
     * 从县返回时获取城市数据
     */
    private void loadCity(int provinceCode) {
        showProgressDialog();
        presenter.loadCity(provinceCode);
    }

    /**
     * 加载县
     */
    private void loadCountry(int provinceId, int cityId) {
        showProgressDialog();
        presenter.loadCountry(provinceId, cityId);
    }


    /**
     * 显示进度框
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("正在加载");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    /**
     * 隐藏
     */
    private void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * 销毁
     */
    private void destroyDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            progressDialog = null;
        }
    }


    /**
     * 加载省份成功
     *
     * @param data
     */
    @Override
    public void onLoadProvinceSuccess(List<Province> data) {
        hideProgressDialog();
        //在显示省列表
        AreaManager.getInstance().setStatusToProvince();
        tvTitle.setText("中国");
        ivBack.setVisibility(View.GONE);
        mData.clear();
        AreaManager.getInstance().clearThenAddAllProvince(data);
        for (Province p : AreaManager.getInstance().getProvinceList()) {
            mData.add(p.getProvinceName());
        }
        Log.e("size", "" + mData.size());
        adapter.notifyDataSetChanged();
    }

    /**
     * 加载省份失败
     *
     * @param s
     */
    @Override
    public void onLoadProvinceFailure(String s) {
        hideProgressDialog();
        ToastUtil.showToast(s);

    }

    /**
     * 加载城市成功
     *
     * @param data
     */
    @Override
    public void onLoadCitySuccess(List<City> data) {
        hideProgressDialog();
        AreaManager.getInstance().setStatusToCity();
        AreaManager.getInstance().setCurrentProvince(AreaManager.getInstance().getTempSelectedProvince());
        AreaManager.getInstance().setTempSelectedProvince(null);

        for (Province province : AreaManager.getInstance().getProvinceList()) {
            Log.e("province", "" + province);
        }


        if (AreaManager.getInstance().getCurrentProvince() != null) {
            tvTitle.setText(AreaManager.getInstance().getCurrentProvince().getProvinceName());
        }
        ivBack.setVisibility(View.VISIBLE);
        mData.clear();
        AreaManager.getInstance().clearThenAddAllCity(data);
        for (City p : AreaManager.getInstance().getCityList()) {
            mData.add(p.getCityName());
        }
        adapter.notifyDataSetChanged();

    }

    /**
     * 加载城市失败
     *
     * @param s
     */
    @Override
    public void onLoadCityFailure(String s) {
        hideProgressDialog();
        ToastUtil.showToast(s);
    }

    /**
     * 加载县成功
     *
     * @param data
     */
    @Override
    public void onLoadCountrySuccess(List<Country> data) {
        hideProgressDialog();
        AreaManager.getInstance().setStatusToCountry();
        AreaManager.getInstance().setCurrentCity(AreaManager.getInstance().getTempSelectedCity());
        AreaManager.getInstance().setTempSelectedCity(null);

        if (AreaManager.getInstance().getCurrentCity() != null) {
            tvTitle.setText(AreaManager.getInstance().getCurrentCity().getCityName());
        }
        ivBack.setVisibility(View.VISIBLE);
        mData.clear();
        AreaManager.getInstance().clearThenAddAllCountry(data);
        for (Country p : AreaManager.getInstance().getCountryList()) {
            mData.add(p.getCountyName());
        }
        adapter.notifyDataSetChanged();
    }

    /**
     * 加载县失败
     *
     * @param s
     */
    @Override
    public void onLoadCountryFailure(String s) {
        hideProgressDialog();
        ToastUtil.showToast(s);
    }

    @Override
    public void onDestroy() {
        destroyDialog();
        super.onDestroy();
    }
}
