package com.android.hyb.widget.pop;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.ui.adapter.ListSingleSelectAdapter;
import com.android.hyb.util.NotchScreenUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

/**
 * 自定义单选PopupWindow
 * Created by luojialun on 2018/9/29.
 */

public class ListSingleSelectPop {

    private PopupWindow popupWindow;
    private View mPopupWindowView;
    private RecyclerView mRecyclerView;
    private TextView mCancel;
    private OnItemClickListener mListener;
    private RelativeLayout mContainer;

    public ListSingleSelectPop(Context context, List<String> contentList) {
        init(context, contentList);
    }

    private void init(Context context, final List<String> contentList) {
        popupWindow = new PopupWindow(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        mPopupWindowView = LayoutInflater.from(context).inflate(R.layout.pop_list_single_select, null);
        popupWindow.setContentView(mPopupWindowView);
        popupWindow.setClippingEnabled(NotchScreenUtils.checkDeviceHasNavigationBar(context));
        popupWindow.setAnimationStyle(R.style.pop_animation2);
        mRecyclerView = mPopupWindowView.findViewById(R.id.content_rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        ListSingleSelectAdapter adapter = new ListSingleSelectAdapter(contentList);
        mRecyclerView.setAdapter(adapter);
        mCancel = mPopupWindowView.findViewById(R.id.tv_cancel);
        mContainer = mPopupWindowView.findViewById(R.id.rl_container);

        //点击其他区域dismiss
        mContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return false;
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (mListener != null) {
                    mListener.setOnItemClick(position, contentList.get(position));
                    popupWindow.dismiss();
                }
            }
        });

        //点击取消条目
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    popupWindow.dismiss();
                }
            }
        });
    }

    public void showPopupWindow(View view) {
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
    }

    public boolean isPopShowing() {
        return null == popupWindow ? false : popupWindow.isShowing();
    }

    public void dissmiss() {
        if (null != popupWindow && popupWindow.isShowing()) {
            popupWindow.dismiss();
        }
    }

    public interface OnItemClickListener {
        void setOnItemClick(int position, String typeContent);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }
}
