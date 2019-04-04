package com.android.hyb.widget.pop;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.hyb.R;

public class MainTipPop {

    private PopupWindow mPopupWindow;
    private Activity activity;


    public MainTipPop(Activity activity) {
        this.activity = activity;
        initPopupWindow();
    }


    private void initPopupWindow() {
        if (null != mPopupWindow) {
            return;
        }
        View mPopView = activity.getLayoutInflater().inflate(R.layout.pop_main_tip, null);
        TextView sureTv = mPopView.findViewById(R.id.sure_tv);
        sureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
