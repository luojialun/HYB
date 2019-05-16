package com.android.hyb.ui.acitvity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.android.hyb.R;
import com.android.hyb.base.BaseActivity;
import com.android.hyb.bean.clazz.UserInfo;
import com.android.hyb.bean.response.GetPageGroupsResponse;
import com.android.hyb.net.exception.ErrorException;
import com.android.hyb.net.factory.ServiceFactory;
import com.android.hyb.net.observer.ToastObserver;
import com.android.hyb.net.service.ContentService;
import com.android.hyb.net.transformer.RemoteTransformer;
import com.android.hyb.ui.adapter.TeamAdapter;
import com.android.hyb.util.ConstUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import butterknife.BindView;

public class MineTeamActivity extends BaseActivity {

    @BindView(R.id.sale_tv)
    TextView saleTv;
    @BindView(R.id.market_tv)
    TextView marketTv;
    @BindView(R.id.member_tv)
    TextView memberTv;
    @BindView(R.id.content_rv)
    RecyclerView contentRv;

    private int pageIndex = 1;
    private TeamAdapter adapter;

    @Override
    public int setViewId() {
        return R.layout.activity_mine_team;
    }

    @Override
    public void initView() {
        contentRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TeamAdapter(null);
        contentRv.setAdapter(adapter);
        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                pageIndex++;
                getPageGroups();
            }
        }, contentRv);
    }

    @Override
    public void initData() {
        getPageGroups();
    }

    public void getPageGroups() {
        showProgress();
        ServiceFactory.createHYBService(ContentService.class).getPageGroups(UserInfo.getToken(), pageIndex, ConstUtils.PAGE_SIZE)
                .compose(new RemoteTransformer<>())
                .subscribe(new ToastObserver<GetPageGroupsResponse>(this) {
                    @Override
                    public void onNext(GetPageGroupsResponse response) {
                        dismissProgress();
                        if (null != response && 0 < response.getData().size()) {
                            if (1 == pageIndex) {
                                adapter.setNewData(response.getData());
                            } else {
                                adapter.addData(response.getData());
                            }

                            if (response.getData().size() < ConstUtils.PAGE_SIZE) {
                                adapter.loadMoreEnd();
                            } else {
                                adapter.loadMoreComplete();
                            }
                        } else {
                            adapter.setNewData(null);
                            adapter.setEmptyView(R.layout.layout_empty);
                        }
                    }

                    @Override
                    public void onError(ErrorException e) {
                        super.onError(e);
                        dismissProgress();
                    }
                });
    }
}
