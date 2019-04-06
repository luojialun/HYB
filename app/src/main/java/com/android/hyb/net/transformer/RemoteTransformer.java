package com.android.hyb.net.transformer;

import com.android.hyb.base.BaseResponse;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;


public class RemoteTransformer<Z extends BaseResponse.ErrorResponse>
        implements ObservableTransformer<BaseResponse<Z>, Z> {

    @Override
    public Observable<Z> apply(@NonNull Observable<BaseResponse<Z>> upstream) {
        return upstream
                .compose(new SchedulerTransformer<BaseResponse<Z>>())
                .compose(new ErrorCheckerTransformer<Z>());
    }


}