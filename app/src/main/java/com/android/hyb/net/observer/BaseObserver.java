package com.android.hyb.net.observer;


import android.content.Context;

import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.exception.ExceptionEngine;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {

    private WeakReference<Context> contextRef;

    public BaseObserver(Context context) {
        contextRef = new WeakReference<>(context);
    }

    protected Context getContext() {
        return contextRef == null ? null : contextRef.get();
    }

    @Override
    public void onError(Throwable t) {
        final Context context = contextRef.get();
        if (context == null) return;

        onError(ExceptionEngine.handleException(t));
        t.printStackTrace();
    }

    public abstract void onError(ErrorException e);

    @Override
    public void onComplete() {

    }

    @Override
    public void onSubscribe(Disposable d) {

    }

}
