package com.android.hyb.net.observer;

import android.content.Context;

import com.android.hyb.net.exception.ErrorException;

public abstract class ToastObserver<T> extends BaseObserver<T> {

    public ToastObserver(Context context) {
        super(context);
    }

    @Override
    public void onError(ErrorException e) {
        if (null != getContext()) {
            doBizErrorCodeAction(getContext(), e);
        }
    }

    private void doBizErrorCodeAction(Context context, ErrorException e) {

    }


}
