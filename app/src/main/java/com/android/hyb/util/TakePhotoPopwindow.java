package com.android.hyb.util;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.hyb.R;

public class TakePhotoPopwindow implements View.OnClickListener {

    private Activity activity;
    private PopupWindow mPopWindow;

    private OnTakePhotoCallBackListener callBackListener;
    public void setCallBackListener(OnTakePhotoCallBackListener callBackListener) {
        this.callBackListener = callBackListener;
    }

    public static interface OnTakePhotoCallBackListener {
        public void OnClickTakePhotoButton();
        public void OnClickOpenPhotoButton();
    }

    public TakePhotoPopwindow(Activity activity){
        this.activity = activity;
    }

    /**
     * 从底部弹出popupwindow
     */
    public void showBottomPop(View parent) {
        final View popView = View.inflate(activity, R.layout.bottom_pop_layout, null);
        showAnimation(popView);//开启动画
        mPopWindow = new PopupWindow(popView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mPopWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        mPopWindow.showAtLocation(parent,
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        mPopWindow.setOutsideTouchable(true);
        mPopWindow.setFocusable(true);
        mPopWindow.update();
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.7f;
        activity.getWindow().setAttributes(lp);
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                dismiss();
            }
        });


        popView.findViewById(R.id.takePhotoPop_takePhoto).setOnClickListener(this);
        popView.findViewById(R.id.takePhotoPop_openPhotos).setOnClickListener(this);
        popView.findViewById(R.id.takePhotoPop_cancel).setOnClickListener(this);
    }

    private void dismiss(){
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1f;
        activity.getWindow().setAttributes(lp);
        mPopWindow.dismiss();
    }

    /**
     * 给popupwindow添加动画
     *
     * @param popView
     */
    private void showAnimation(View popView) {
        AnimationSet animationSet = new AnimationSet(false);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1.0f);
        alphaAnimation.setDuration(300);
        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0f,
                Animation.RELATIVE_TO_SELF, 1f, Animation.RELATIVE_TO_SELF, 0f
        );
        translateAnimation.setDuration(300);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        popView.startAnimation(animationSet);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.takePhotoPop_takePhoto:
                takePhoto();
                break;
            case R.id.takePhotoPop_openPhotos:
                openPhoto();
                break;
            case R.id.takePhotoPop_cancel:
                dismiss();
                break;
        }
    }

    private void takePhoto(){
        if (callBackListener != null)
        {
            callBackListener.OnClickTakePhotoButton();
        }
        dismiss();
    }

    private void openPhoto(){
        if (callBackListener != null)
        {
            callBackListener.OnClickOpenPhotoButton();
        }
        dismiss();
    }

}


