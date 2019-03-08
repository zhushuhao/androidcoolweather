package dao.d.com.android.coolweather;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import dao.d.com.android.coolweather.api.ExceptionHandle;

public abstract class BaseSubscriber<T> implements Subscriber<T> {

    @Override
    public void onSubscribe(Subscription s) {
        s.request(1);
    }


    @Override
    public void onNext(T t) {
        onSuccess(t);
    }


    @Override
    public void onError(Throwable t) {
        onFail(ExceptionHandle.handleException(t));
    }


    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFail(ExceptionHandle.ResponseThrowable e);


}
