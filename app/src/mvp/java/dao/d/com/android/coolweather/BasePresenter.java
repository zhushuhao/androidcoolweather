package dao.d.com.android.coolweather;

import com.trello.rxlifecycle2.components.RxFragment;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

public abstract class BasePresenter {
    protected RxAppCompatActivity mActivity;
    protected RxFragment mFragment;
}
