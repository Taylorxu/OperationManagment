package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.renderscript.Element;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.EventClassificationActivity;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 树形选择组件
 */
public class TreeSelectionView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {
    private TextView tv_tree_left;
    private TextView tv_tree_right;
    private RelativeLayout rl_tree_selection;
    private LoadingView loadingView;
    private WorkOrder wo;
    private List<DictDatasBean> list1;

    public TreeSelectionView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.tree_selection_view, this, true);
        tv_tree_left = (TextView) view.findViewById(R.id.tv_tree_left);
        tv_tree_right = (TextView) view.findViewById(R.id.tv_tree_right);
        rl_tree_selection = (RelativeLayout) view.findViewById(R.id.rl_tree_selection);
    }


    public void setData(final WorkOrder wo) {
        wo.addChangeListener(this);
        this.wo = wo;
        String title = wo.getName();
        final String content = wo.getValue();
        final String param = wo.getSrclib();
//        queryValidCiByModelName(param, content);//为控件赋值
        String dicValue = WorkOrderDataManager.newInstance().getDicValue(wo.getValue());
        tv_tree_right.setText(dicValue);
        if (!wo.isModified()) {
            rl_tree_selection.setFocusable(false);
            rl_tree_selection.setClickable(false);
            rl_tree_selection.clearFocus();
        }
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_tree_left.setText(title + " *");
            } else {
                tv_tree_left.setText(title);
            }
        }

        rl_tree_selection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                List<EventClassificationModel> datas = pickOutRootData();
                EventClassificationActivity.startSelf(getContext(), datas, wo.getID());
            }
        });
    }


    /**
     * 查询单个字典有效数据
     *
     * @param param
     * @param content
     */
    private void queryValidCiByModelName(String param, final String content) {
        List<String> list = new ArrayList<>();
        list.add(param);
        loadingView = LoadingView.getLoadingView(getContext());
        loadingView.show();
        ApiService.Creator.get().queryValidCiByModelName(RequestBody.getgEnvelope(Protocol.dict_name_space, list, Protocol.queryValidCiByModelName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<DictDatas>>())
                .flatMap(new FlatMapTopRes<DictDatas>(DataTypeSelector.RE))
                .subscribe(new Subscriber<DictDatas>() {
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
                    public void onNext(DictDatas dictDatasBeans) {
                        list1 = dictDatasBeans.getDictDatas();
                        if (!TextUtils.isEmpty(content)) {
                            if (list1 != null) {
                                for (DictDatasBean bean : list1) {
                                    if (bean.getDictId().equals(content)) {
                                        tv_tree_right.setText(bean.getDictName());
                                    }
                                }
                            }
                        } else {
                            tv_tree_right.setText("");
                        }
                        loadingView.stop(loadingView);
                    }
                });
    }


    private List<EventClassificationModel> pickOutRootData() {
        List<EventClassificationModel> datas = new ArrayList<>();
        for (DictDatasBean bean : list1) {
            if ("DICT_ROOT".equals(bean.getDictParentValue())) {
                datas.add(newEventClassificationModel(bean));
            }
        }
        fillChildrenList(datas);
        return datas;
    }

    public void fillChildrenList(List<EventClassificationModel> datas) {
        for (EventClassificationModel model : datas) {
            arrangeChilder(model);
        }
        for (EventClassificationModel model : datas) {
            for (EventClassificationModel model1 : model.getList()) {
                arrangeChilder(model1);
            }
        }
    }

    private void arrangeChilder(EventClassificationModel model) {
        List<EventClassificationModel> datas1 = new ArrayList<>();
        for (DictDatasBean bean : list1) {
            if (model.getDictValue().equals(bean.getDictParentValue())) {
                datas1.add(newEventClassificationModel(bean));
            }
        }
        model.setList(datas1);
    }

    private EventClassificationModel newEventClassificationModel(DictDatasBean bean) {
        EventClassificationModel model1 = new EventClassificationModel();
        model1.setDictName(bean.getDictName());
        model1.setDictId(bean.getDictId());
        model1.setDictParentValue(bean.getDictParentValue());
        model1.setDictValue(bean.getDictValue());
        return model1;
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        setData(workOrder);
    }
}
