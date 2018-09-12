package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.CheckBoxViewAdapter;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.CallBack;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.EventClassificationActivity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;

/**
 * 复选框
 * Created by ycs on 2016/12/1.
 */
public class CheckBoxView extends LinearLayout implements CheckBoxViewAdapter.ICheckBoxViewListener, RealmObjectChangeListener<WorkOrder> {

    private TextView tv_check_box;
    private RecyclerView rv_check_box;
    private CheckBoxViewAdapter adapter;
    private List<DictDatasBean> datas = new ArrayList<>();
    private WorkOrder wo;

    public CheckBoxView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.check_box_view, this, true);
        tv_check_box = (TextView) view.findViewById(R.id.tv_check_box_view);
        rv_check_box = (RecyclerView) view.findViewById(R.id.rv_check_box_view);

        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 3);
        rv_check_box.setLayoutManager(manager);
        adapter = new CheckBoxViewAdapter(getContext());
        rv_check_box.setAdapter(adapter);
        adapter.setOnICheckBoxListener(this);
    }

    public void setData(WorkOrder wo) {
        final List<DictDatasBean> lists = new ArrayList<>();
        this.wo = wo;
        adapter.setClick(wo.isModified());
        wo.addChangeListener(this);
        String str = wo.getName();
        String value = wo.getValue();
        if (!TextUtils.isEmpty(str)) {
            if (wo.isRequired()) {
                tv_check_box.setText(str + " *");
            } else {
                tv_check_box.setText(str);
            }
        }
        WorkOrderDataManager.newInstance().getDictDatasBySrclib(wo.getSrclib(), new CallBack<List<DictDatasBean>>() {
            @Override
            public void onResponse(List<DictDatasBean> dictDatasBeans) {
                lists.addAll(dictDatasBeans);
                adapter.fillData(lists);
            }
        });

        List<String> list = new ArrayList<>();
        if (!TextUtils.isEmpty(value)) {
            String[] ids = value.split(",");
            for (DictDatasBean bean : lists) {
                for (String id : ids) {
                    if (bean.getDictId().equals(id)) {
                        list.add(id);
                    }
                }
            }
            if (list.size() == 0) {
                for (DictDatasBean bean : lists) {
                    for (String id : ids) {
                        if (bean.getDictName().equals(id)) {
                            list.add(bean.getDictId());
                        }
                    }

                }
                //当WorkOrder的value是字典的name而不是id
                WorkOrderDataManager.newInstance().modifyValue(wo.getID(), toStringByIds(list));
            }
        }
        //选中的值的id集合
        adapter.setIds(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setOnItemClickListenenr() {
        List<String> ids = adapter.getIds();
        WorkOrderDataManager.newInstance().modifyValue(wo.getID(), toStringByIds(ids));
    }

    private String toStringByIds(List<String> ids) {
        String idString = ids.toString();
        String result = idString.substring(1, idString.length() - 1);
        return result;
    }

    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) return;
        setData(workOrder);
    }
}
