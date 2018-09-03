package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ColumnsProperty;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;

import java.util.List;

/**
 * 下拉控件
 * Created by ycs on 2016/12/5.
 */

public class BottomViewForDataGrid extends RelativeLayout implements View.OnClickListener {

    private List<DictDatasBean> datas;
    private BaseView baseView;
    private WorkOrder wo;
    private String key, value;

    public BottomViewForDataGrid(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.bottom_view, this, true);
        baseView = (BaseView) view.findViewById(R.id.bv_bottom_view);
        baseView.setOnClickListener(this);

    }

    /**
     * @param wo
     * @param datas
     * @param b
     * @param dicKey
     */
    public void setData2(WorkOrder wo, List<DictDatasBean> datas, ColumnsProperty b, String dicKey) {
        this.wo = wo;
        this.datas = datas;
        String title = b.getName();
        String value = "";
        if (!TextUtils.isEmpty(dicKey))
            for (DictDatasBean bean : datas) {
                if (bean.getDictId().equals(dicKey)) {
                    value = bean.getDictName();
                    WorkOrderDataManager.newInstance().modifyValue(wo.getID(), dicKey);
                }
            }

        if (!TextUtils.isEmpty(title)) {
            if (b.isRequired()) {
                baseView.setTv_left(title + " *");
            } else {
                baseView.setTv_left(title);
            }
        }
        if (!TextUtils.isEmpty(value)) {
            baseView.setTv_right(value);
        } else {
            baseView.setTv_right("");
        }

    }

    @Override
    public void onClick(View view) {
        final BottomDialog dialog = new BottomDialog(getContext(), R.style.add_dialog);
        dialog.setUpData(datas);
        dialog.setOnTitleClickListener(new BottomDialog.OnTitleClickListener() {
            @Override
            public void onTitleRightClickListener(String province, String name) {
                baseView.setTv_right(name);
                value = province;
            }
        });
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return baseView.getTvRightText();
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
