package com.android.hyb.net.transformer;

import android.text.TextUtils;

import com.android.hyb.base.BaseResponse;
import com.android.hyb.net.exception.BussinessError;
import com.android.hyb.net.exception.NetError;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;

public class ErrorCheckerTransformer<Z>
        implements ObservableTransformer<BaseResponse<Z>, Z> {

    @Override
    public Observable<Z> apply(Observable<BaseResponse<Z>> upstream) {
        return upstream.map(new Function<BaseResponse<Z>, Z>() {

            @Override
            public Z apply(BaseResponse<Z> z) throws Exception {
                String msg = null;

                String status = "";
                if (!z.status.equals(BaseResponse.STATUS_SUCCESS)) {
                    //code = z.data.error_code;
                    status = z.status;
                    msg = z.message;

                    if (TextUtils.isEmpty(msg)) {
                        msg = NetError.MSG_SERVICE_ERROR;
                    }
                    //state = 1;
                }

                if (!TextUtils.isEmpty(msg)) {
                    try {
                        throw new BussinessError(status, msg);
                    } catch (BussinessError e) {
                        throw Exceptions.propagate(e);
                    }
                }

                return z.data;
            }
        });
    }

}


