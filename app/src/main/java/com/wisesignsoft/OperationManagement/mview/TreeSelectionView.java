package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.CallBack;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.EventClassificationActivity;
import com.wisesignsoft.OperationManagement.utils.LogUtil;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 树形选择组件
 * xuzhiguang
 * 点击时根据字典模型编码去查询相应的字典值。得到的结果作为参数传给 选择界面
 */
public class TreeSelectionView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {
    private TextView tv_tree_left;
    private TextView tv_tree_right;
    private RelativeLayout rl_tree_selection;
    private List<DictDatasBean> list1 = new ArrayList<>();

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
        final String woId = wo.getID();
        String title = wo.getName();
        final String content = wo.getValue();
        WorkOrderDataManager.newInstance().getDicValueById(content, new CallBack<String>() {
            @Override
            public void onResponse(String dicValue) {
                if (!TextUtils.isEmpty(dicValue)) {
                    tv_tree_right.setText(dicValue);
                } else {
                    tv_tree_right.setText("");
                }
            }
        });

        if (!wo.isModified()) {
            rl_tree_selection.setEnabled(false);
        }
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_tree_left.setText(title + " *");
            } else {
                tv_tree_left.setText(title);
            }
        }
        final android.os.Handler handler = new android.os.Handler();
        rl_tree_selection.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!wo.isModified()) {
                    ToastUtil.toast(getContext(), "不可编辑");
                }
                final LoadingView loadingView = LoadingView.getLoadingView(getContext());
                loadingView.show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        WorkOrderDataManager.newInstance().getDictDatasBySrclib(wo.getSrclib(), new CallBack<List<DictDatasBean>>() {
                            @Override
                            public void onResponse(List<DictDatasBean> dictDatasBeans) {
                                list1 = new ArrayList<>();
                                list1.addAll(dictDatasBeans);
                                List<EventClassificationModel> datas = pickOutRootData();
                                EventClassificationActivity.startSelf(getContext(), datas, woId);
                                loadingView.stop(loadingView);
                            }
                        });
                    }
                }, 2000);


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
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
