package com.android.hyb.net.transformer;

import com.android.hyb.base.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;


public class RemoteTransformer<Z extends BaseResponse> implements ObservableTransformer<Z, Z> {

    @Override
    public Observable<Z> apply(@NonNull Observable<Z> upstream) {
        return upstream
                .compose(new SchedulerTransformer<Z>())
                .compose(new ErrorCheckerTransformer<Z>());
    }


}