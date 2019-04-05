package com.android.hyb.widget.pop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.GlideApp;
import com.android.hyb.util.ToastUtils;

public class ShareCodePop {

    private PopupWindow mPopupWindow;
    private Activity activity;


    public ShareCodePop(Activity activity) {
        this.activity = activity;
        initPopupWindow();
    }


    private void initPopupWindow() {
        if (null != mPopupWindow) {
            return;
        }
        View mPopView = activity.getLayoutInflater().inflate(R.layout.pop_share_code, null);
        TextView cancelTv = mPopView.findViewById(R.id.cancel_tv);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissmiss();
            }
        });

        ImageView goodsIv=mPopView.findViewById(R.id.goods_iv);
        GlideApp.with(activity).load("http://b-ssl.duitang.com/uploads/item/201505/13/20150513184342_wHXaf.jpeg").into(goodsIv);

        TextView saveTv=mPopView.findViewById(R.id.save_tv);
        saveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(activity,"保存成功");
                dissmiss();
            }
        });
        mPopupWindow = new PopupWindow(mPopView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.setClippingEnabled(false);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setAnimationStyle(R.style.pop_animation2);
    }

    public void showPopupWindow(View view) {
        if (null != mPopupWindow && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        }
    }

    public void showPopupWindow() {
        if (null != mPopupWindow && !mPopupWindow.isShowing()) {
            mPopupWindow.showAtLocation(activity.getWindow().getDecorView().getRootView(), Gravity.CENTER, 0, 0);
        }
    }

    public void dissmiss() {
        if (null != mPopupWindow && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
        }
    }
}
