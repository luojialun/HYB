package com.android.hyb.net.transformer;

import com.android.hyb.base.BaseResponse;
import com.android.hyb.net.exception.BussinessError;
import com.android.hyb.net.exception.MMError;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;

public class ErrorCheckerTransformer<Z extends BaseResponse.ErrorResponse>
        implements ObservableTransformer<BaseResponse<Z>, Z> {

    @Override
    public Observable<Z> apply(Observable<BaseResponse<Z>> upstream) {
        return upstream.map(new Function<BaseResponse<Z>, Z>() {

            @Override
            public Z apply(BaseResponse<Z> z) throws Exception {
                String msg = null;
                int code = -1;
                int state = 0;
                if (z.data.errorCode != BaseResponse.CODE_SUCCESS) {
                    code = z.data.errorCode;
                    msg = z.data.erroeMsg;
                    if (msg == null) {
                        msg = MMError.MSG_SERVICE_ERROR;
                    }
                    state = 1;
                }

                if (msg != null) {
                    try {
                        throw new BussinessError(code, msg, state);
                    } catch (BussinessError e) {
                        throw Exceptions.propagate(e);
                    }
                }

                return z.data;
            }
        });
    }

}


