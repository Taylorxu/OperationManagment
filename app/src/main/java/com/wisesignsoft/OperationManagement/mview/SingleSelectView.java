package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.NewWorkOrderAdapter;
import com.wisesignsoft.OperationManagement.adapter.SingleSelectViewAdapter;
import com.wisesignsoft.OperationManagement.bean.CanCreateProcessBean;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.CallBack;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 单选组件
 * Created by ycs on 2016/12/2.
 */
public class SingleSelectView extends LinearLayout implements RealmObjectChangeListener<WorkOrder> {

    private TextView tv_single_select;
    private RecyclerView rv_single_select;
    private NewWorkOrderAdapter adapterSelf;
    private WorkOrder wo;
    private SingleSelectViewAdapter adapter;
    private List<DictDatasBean> list;
    private List<DictDatasBean> datas = new ArrayList<>();

    public SingleSelectView(Context context) {
        super(context);
        init(context);
    }

    public SingleSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_select_view, this, true);
        tv_single_select = (TextView) view.findViewById(R.id.tv_single_select_view);
        rv_single_select = (RecyclerView) view.findViewById(R.id.rv_single_select_view);

        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 3);
        rv_single_select.setLayoutManager(manager);
        adapter = new SingleSelectViewAdapter(getContext(), datas);
        rv_single_select.setAdapter(adapter);
    }

    public void setData(final WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        final String value = wo.getValue();
        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_single_select.setText(title + " *");
            } else {
                tv_single_select.setText(title);
            }
        }
        adapter.setClick(wo.isModified());
        //根据字典初始化组件 例如 ‘是否立即开工’
        WorkOrderDataManager.newInstance().getDictDatasBySrclib(wo.getSrclib(), new CallBack<List<DictDatasBean>>() {
            @Override
            public void onResponse(List<DictDatasBean> dictDatasBeans) {
                if (dictDatasBeans.size() > 0) {
                    list = new ArrayList<>();
                    list.addAll(dictDatasBeans);
                    datas.clear();
                    for (DictDatasBean datasBean : dictDatasBeans) {
                        if (value.equals(datasBean.getDictId()))
                            adapter.setCheckedId(value);

                    }

                    datas.addAll(dictDatasBeans);
                    adapter.notifyDataSetChanged();
                }
            }
        });


        adapter.setOnISingleSelectClickListener(new SingleSelectViewAdapter.ISingleSelectViewClickListener() {
            @Override
            public void setOnISingleSelectViewClick(int position) {
                WorkOrderDataManager.newInstance().modifyValue(wo.getID(), list.get(position).getDictId());
            }
        });

    }


    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setData(workOrder);
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
