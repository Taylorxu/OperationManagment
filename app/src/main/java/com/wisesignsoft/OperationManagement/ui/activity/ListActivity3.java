package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ColumnsProperty;
import com.wisesignsoft.OperationManagement.bean.Dict;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.ResColumnsJsonBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.CallBack;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.BottomViewForDataGrid;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.SingleTextView;
import com.wisesignsoft.OperationManagement.mview.TimeViewForDataGrid;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ListActivity3 extends BaseActivity {

    private MyTitle mt_list;
    private LinearLayout ll_list;
    private List<ResColumnsJsonBean> datas;
    private Map<String, Dict> map;
    private String id;
    private WorkOrder my_work_order;
    private int deletePosition = 0;

    public static void startSelf(Context context, Map<String, Dict> map, ArrayList<ResColumnsJsonBean> datas, WorkOrder workOrder, int deletePosition) {
        Intent intent = new Intent(context, ListActivity3.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("map", (Serializable) map);
        bundle.putSerializable("workOrder", workOrder);
        intent.putParcelableArrayListExtra("datas", datas);
        intent.putExtras(bundle);
        intent.putExtra("deletePosition", deletePosition);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        init();
    }

    private void init() {
        map = (Map<String, Dict>) getIntent().getSerializableExtra("map");
        datas = getIntent().getParcelableArrayListExtra("datas");
        my_work_order = (WorkOrder) getIntent().getSerializableExtra("workOrder");
        deletePosition = getIntent().getIntExtra("deletePosition", 0);
        id = my_work_order.getID();
        mt_list = (MyTitle) findViewById(R.id.mt_list);
        ll_list = (LinearLayout) findViewById(R.id.ll_list_activity);

        mt_list.setBack(true, this);
        mt_list.setTitle("列表");
        mt_list.setTvRight(true, "确定", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getValue();
            }
        });
        setData(this);
    }

    private void setData(Context context) {
        String allColumnsJson = my_work_order.getAllColumnsJson();
        Gson gson = new Gson();
        List<ColumnsProperty> allColumnsJsons = gson.fromJson(allColumnsJson, new TypeToken<List<ColumnsProperty>>() {
        }.getType());
        for (final ColumnsProperty b : allColumnsJsons) {
            switch (b.getType()) {
                case "TextInput":
                    SingleTextView singleTextView = new SingleTextView(context);
                    ll_list.addView(singleTextView);
                    String value = "";
                    if (map.size() != 0) {
                        value = map.get(b.getDmAttrName()).getValue();
                    }
                    singleTextView.setData(b.getName(), value, b.isRequired(), b.isHasModified());
                    singleTextView.setKey(b.getDmAttrName());
                    break;
                case "ComboBox":    //组合框
                    final BottomViewForDataGrid bottomView = new BottomViewForDataGrid(context);
                    bottomView.setKey(b.getDmAttrName());
                    ll_list.addView(bottomView);
                    switch (b.getName()) {
                        case "SUB_TA":
                            WorkOrderDataManager.newInstance().getDictDatasBySrclib("province", new CallBack<List<DictDatasBean>>() {
                                @Override
                                public void onResponse(List<DictDatasBean> dictDatasBeans) {
                                    String dicKey = "";
                                    if (map.size() > 0) {
                                        dicKey = map.get(b.getDmAttrName()).getValue();
                                    }
                                    bottomView.setData2(my_work_order, dictDatasBeans, b, dicKey);
                                }
                            });
                            break;
                    }
                    break;

                case "DateFeild":
                    TimeViewForDataGrid timeView = new TimeViewForDataGrid(context);
                    ll_list.addView(timeView);
                    timeView.setData(my_work_order, b, map);
                    timeView.setKey(b.getDmAttrName());
                    break;

            }
        }
    }

    private void getValue() {
        int ii = ll_list.getChildCount();
        if (ii > 0) {
            for (int i = 0; i < ii; i++) {
                View view = ll_list.getChildAt(i);
                String key = "", value = "", name = "";
                Dict dict = new Dict();
                if (view instanceof SingleTextView) {
                    key = ((SingleTextView) view).getKey();
                    value = ((SingleTextView) view).getValue();
                    name = value;
                } else if (view instanceof BottomViewForDataGrid) {
                    key = ((BottomViewForDataGrid) view).getKey();
                    value = ((BottomViewForDataGrid) view).getValue();
                    name = ((BottomViewForDataGrid) view).getName();
                } else if (view instanceof TimeViewForDataGrid) {
                    key = ((TimeViewForDataGrid) view).getKey();
                    value = ((TimeViewForDataGrid) view).getValue();
                    name = ((TimeViewForDataGrid) view).getValue();
                }

                for (ResColumnsJsonBean b : datas) {
                    if (b.getDataFieldObj().getName().equals(key) && b.getRequiredObj().getValue() == 1) {//组件是同一个&& 必填
                        if (value.length() == 0) {
                            ToastUtil.toast(getBaseContext(), "请填写" + b.getHeaderText());
                            return;
                        }
                    }
                }

                dict.setKey(key);
                dict.setValue(value);
                dict.setName(name);
                map.put(key, dict);
            }
        }

        String value = WorkOrderDataManager.newInstance().getValueById(id);
        Gson gson = new Gson();
        List<Map<String, Dict>> list = gson.fromJson(value, new TypeToken<List<Map<String, Dict>>>() {
        }.getType());
        if (list == null) list = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (i == deletePosition) {
                list.remove(deletePosition);
                break;
            }
        }
        list.add(deletePosition, map);
        WorkOrderDataManager.newInstance().modifyValue(id, gson.toJson(list));
        finish(); //关闭当前界面， 详情界面或者新建界面 会 onResume 调用refresh（）
    }


}
