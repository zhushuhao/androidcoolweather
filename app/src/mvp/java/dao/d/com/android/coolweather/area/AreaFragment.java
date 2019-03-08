package dao.d.com.android.coolweather.area;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dao.d.com.android.coolweather.R;
import dao.d.com.android.coolweather.bean.place.City;
import dao.d.com.android.coolweather.bean.place.Country;
import dao.d.com.android.coolweather.bean.place.Province;
import dao.d.com.android.coolweather.utils.ToastUtil;

public class AreaFragment extends Fragment implements AreaContract.View {

    private ImageView ivBack;
    private ListView lv;
    private ArrayAdapter<String> adapter;
    private ProgressDialog progressDialog;
    private TextView tvTitle;

    private List<String> mData = new ArrayList<>();

    private AreaPresenter presenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        tvTitle = view.findViewById(R.id.tv_title);
        lv = view.findViewById(R.id.lv);
        ivBack = view.findViewById(R.id.iv_back);
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, mData);

        presenter = new AreaPresenter(this);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AreaManager.getInstance().isCountry()) {
                    showProgressDialog();
                    presenter.loadCity(new HashMap<>());
                } else if (AreaManager.getInstance().isCity()) {
                    presenter.loadProvince(new HashMap<>());
                }
            }
        });
        return view;
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
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
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
        AreaManager.getInstance().setStatusToProvince();
        tvTitle.setText("中国");
        ivBack.setVisibility(View.GONE);
        mData.clear();
        for (Province p : data) {
            mData.add(p.getProvinceName());
        }
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

        if (AreaManager.getInstance().getProvince() != null) {
            tvTitle.setText(AreaManager.getInstance().getProvince().getProvinceName());
        }
        ivBack.setVisibility(View.VISIBLE);
        mData.clear();
        for (City p : data) {
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

        if (AreaManager.getInstance().getCity() != null) {
            tvTitle.setText(AreaManager.getInstance().getCity().getCityName());
        }
        ivBack.setVisibility(View.VISIBLE);
        mData.clear();
        for (Country p : data) {
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
}
