package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.Children;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.AllDeptTreeActivity;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wisesignsoft.OperationManagement.Protocol.dept_name_space;
import static com.wisesignsoft.OperationManagement.Protocol.findAllDept;
import static com.wisesignsoft.OperationManagement.Protocol.findDeptByIds;

/**
 * 部门单选控件
 */
public class DeptChooseView extends FrameLayout {
    private BaseView bv_dept_choose;
    private WorkOrder wo;

    public DeptChooseView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.dept_choose, this, true);
        bv_dept_choose = (BaseView) view.findViewById(R.id.bv_dept_choose);
        bv_dept_choose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final LoadingView loadingView = LoadingView.getLoadingView(getContext());
                loadingView.show();
                ApiService.Creator.get().findAllDeptTree(RequestBody.getgEnvelope(dept_name_space, null, findAllDept))
                        .observeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .flatMap(new FlatMapResponse<BaseDataResponse<List<Children>>>())
                        .flatMap(new FlatMapTopRes<List<Children>>(DataTypeSelector.RES))
                        .subscribe(new Subscriber<List<Children>>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                loadingView.stop(loadingView);
                                e.printStackTrace();
                                EEMsgToastHelper.newInstance().selectWitch(e.getMessage());

                            }

                            @Override
                            public void onNext(List<Children> children) {
                                loadingView.stop(loadingView);
                                AllDeptTreeActivity.startSelf(context, children, wo.getID());
                            }
                        });

            }
        });
    }

    public void setData(WorkOrder wo) {
        this.wo = wo;
        String title = wo.getName();
        String content = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                bv_dept_choose.setTv_left(title + " *");
            } else {
                bv_dept_choose.setTv_left(title);
            }
        }
        if (!TextUtils.isEmpty(content)) {
            findDeptById(content);
        } else {
            bv_dept_choose.setTv_right("");
        }
        if (!wo.isModified()) {
            bv_dept_choose.clearFocus();
            bv_dept_choose.setFocusable(false);
            bv_dept_choose.setClickable(false);
        } else {
            bv_dept_choose.setFocusable(true);
            bv_dept_choose.setClickable(true);
            bv_dept_choose.setFocusableInTouchMode(true);
        }
    }

    private void findDeptById(String id) {
        List<String> list = new ArrayList<>();
        list.add(id);
        ApiService.Creator.get().findDeptByIds(RequestBody.getgEnvelope(dept_name_space, list, findDeptByIds))
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<List<Children>>>())
                .flatMap(new FlatMapTopRes<List<Children>>(DataTypeSelector.RES))
                .subscribe(new Subscriber<List<Children>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());

                    }

                    @Override
                    public void onNext(List<Children> childrens) {
                        if (childrens != null && childrens.size() > 0) {
                            String name = childrens.get(0).getDeptFullName();
                            bv_dept_choose.setTv_right(name);
                        }
                    }
                });


    }
}
