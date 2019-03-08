package dao.d.com.android.coolweather.area;

import java.util.HashMap;

public class AreaPresenter extends AreaContract.Presenter {

    private AreaContract.View mView;
    private AreaContract.Model mModel;

    private AreaPresenter() {
    }

    public AreaPresenter(AreaContract.View view) {
        mView = view;
        mModel = new AreaModel();
    }

    /**
     * 加载省份
     *
     * @param map
     */
    @Override
    void loadProvince(HashMap<String, Object> map) {

    }

    /**
     * 加载城市
     *
     * @param map
     */
    @Override
    void loadCity(HashMap<String, Object> map) {

    }

    /**
     * 加载县
     *
     * @param map
     */
    @Override
    void loadCountry(HashMap<String, Object> map) {

    }
}
