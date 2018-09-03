package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ColumnsJsonBean;
import com.wisesignsoft.OperationManagement.bean.ColumnsProperty;
import com.wisesignsoft.OperationManagement.bean.Dict;
import com.wisesignsoft.OperationManagement.bean.ResColumnsJsonBean;
import com.wisesignsoft.OperationManagement.bean.ResModelConfigure;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.ui.activity.ListActivity;
import com.wisesignsoft.OperationManagement.ui.activity.ListActivity3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ycs on 2016/12/12.
 */
public class StandBookView extends LinearLayout {
    private int deletePosition = 0;
    private List<ColumnsProperty> allColumnsJsons;

    public int getDeletePosition() {
        return deletePosition;
    }

    public void setDeletePosition(int deletePosition) {
        this.deletePosition = deletePosition;
    }

    private LinearLayout ll_stand_book;
    private TextView tv_stand_book_del;
    private TextView tv_stand_book_title;

    private IStandBookClickListener listener;
    private Map<String, String> map;
    private Map<String, Object> map2;
    private Map<String, Dict> map3;
    private List<ColumnsJsonBean> datas1 = new ArrayList<>();
    private ResModelConfigure datas2;
    private ArrayList<ResColumnsJsonBean> datas3 = new ArrayList<>();
    private int type;
    private String id;
    private WorkOrder my_work_order;
    /*列表*/
    private static final int TYPE1 = 1;
    /*配置项列表*/
    private static final int TYPE2 = 2;
    /*台账列表*/
    private static final int TYPE3 = 3;

    public StandBookView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.stand_book_view, this, true);
        tv_stand_book_title = (TextView) view.findViewById(R.id.tv_stand_book_title);
        tv_stand_book_del = (TextView) view.findViewById(R.id.tv_stand_book_del);
        ll_stand_book = (LinearLayout) view.findViewById(R.id.ll_stand_book);
        tv_stand_book_del.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.setDel();
            }
        });
        // 每条列表组件的点击事件，跳转到列表 表格编辑界面
        ll_stand_book.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (type) {
                    case TYPE1:
                        ListActivity.startSelf(context, map, datas1, id, deletePosition);
                        break;
                    case TYPE2:
                        break;
                    case TYPE3:
                        ListActivity3.startSelf(context, map3, datas3, my_work_order, deletePosition);
                        break;
                }
            }
        });
    }

    public void setData1(Context context, List<ColumnsJsonBean> datas, Map<String, String> map, int type, String id) {
        this.map = map;
        this.id = id;
        if (datas != null) {
            this.datas1.addAll(datas);
        }
        this.type = type;
        for (ColumnsJsonBean d : datas) {
            ListItemView itemView = new ListItemView(context);
            String headerText = d.getHeaderText();
            if (d.getRequiredObj().getValue() == 1) {//必填
                headerText += "*";
            }
            itemView.setDate(headerText, map.get(d.getDataFieldObj().getName()));
            ll_stand_book.addView(itemView);
        }
    }

    public void setData2(Context context, ResModelConfigure datas, Map<String, Object> map, int type) {
        this.map2 = map;
        this.datas2 = datas;
        this.type = type;
        List<ResModelConfigure.AttrDatasOfFormBean> beens = datas.getAttrDatasOfForm();
        for (ResModelConfigure.AttrDatasOfFormBean d : beens) {
            ListItemView itemView = new ListItemView(context);
            itemView.setDate(d.getName(), (String) map.get(d.getDmAttrName()));
            ll_stand_book.addView(itemView);
        }
    }

    public void setData3(Context context, List<ResColumnsJsonBean> datas, Map<String, Dict> map, int type, WorkOrder workOrder) {
        this.map3 = map;
        this.id = workOrder.getID();
        this.my_work_order = workOrder;
        if (datas != null) {
            this.datas3.addAll(datas);
        }
        this.type = type;
        String allColumnsJson = workOrder.getAllColumnsJson();
        Gson gson = new Gson();
        allColumnsJsons = gson.fromJson(allColumnsJson, new TypeToken<List<ColumnsProperty>>() {
        }.getType());

        for (ColumnsProperty d : allColumnsJsons) {
            ListItemView itemView = new ListItemView(context);
            String key = d.getDmAttrName();
            Dict dict = map.get(key);
            String headerText = d.getName();
            if (d.isRequired()) {//必填
                headerText += "*";
            }
            itemView.setEnable(d.isHasModified());
            if (dict == null) {
                itemView.setDate(headerText, null);
            } else {
                if (!TextUtils.isEmpty(dict.getName())) {
                    itemView.setDate(headerText, dict.getName());
                } else {
                    itemView.setDate(headerText, dict.getValue());
                }
            }
            ll_stand_book.addView(itemView);
        }
    }

    public void setOnIStandBookClickListener(IStandBookClickListener listener) {
        this.listener = listener;
    }

    public interface IStandBookClickListener {
        void setDel();
    }

    public void setDeleteHide(boolean modify) {
        tv_stand_book_del.setVisibility(modify ? VISIBLE : GONE);
        ll_stand_book.setEnabled(modify);//不可点击进入编辑
    }
}
