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
import com.wisesignsoft.OperationManagement.bean.ColumnsJsonBean;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.NumberView;
import com.wisesignsoft.OperationManagement.mview.SingleTextView;
import com.wisesignsoft.OperationManagement.mview.TextFieldView;
import com.wisesignsoft.OperationManagement.mview.TimeView;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListActivity extends BaseActivity {

    private MyTitle mt_list;
    private LinearLayout ll_list;
    private List<ColumnsJsonBean> datas;
    private Map<String, String> map;
    private String id;
    private int deletePosition = 0;

    public static void startSelf(Context context, Map<String, String> map, List<ColumnsJsonBean> datas, String id, int deletePosition) {
        Intent intent = new Intent(context, ListActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("map", (Serializable) map);
        bundle.putSerializable("datas", (Serializable) datas);
        intent.putExtras(bundle);
        intent.putExtra("id", id);
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
        map = (Map<String, String>) getIntent().getSerializableExtra("map");
        datas = (List<ColumnsJsonBean>) getIntent().getSerializableExtra("datas");
        id = getIntent().getStringExtra("id");
        deletePosition = getIntent().getIntExtra("deletePosition", 0);
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
        setData1(this);
    }

    private void setData1(Context context) {
        for (ColumnsJsonBean b : datas) {
            ColumnsJsonBean.DataTypeObjBean model = b.getDataTypeObj();
            switch (model.getValue()) {
                case "text":
                    SingleTextView singleTextView = new SingleTextView(context);
                    ll_list.addView(singleTextView);
                    singleTextView.setData(b.getHeaderText(), map.get(b.getDataFieldObj().getName()), b.getRequiredObj().getValue() == 1 ? true : false, true);
                    singleTextView.setKey(b.getDataFieldObj().getName());
                    break;
                case "textArea":
                    TextFieldView textFieldView = new TextFieldView(context);
                    ll_list.addView(textFieldView);
                    textFieldView.setData(b.getHeaderText(), map.get(b.getDataFieldObj().getName()));
                    break;
                case "number":
                    NumberView numberView = new NumberView(context);
                    ll_list.addView(numberView);
                    numberView.setData(b.getHeaderText(), map.get(b.getDataFieldObj().getName()));
                    break;
                case "datetime":
                    TimeView timeView = new TimeView(context);
                    ll_list.addView(timeView);
                    timeView.setData(b.getHeaderText(), map.get(b.getDataFieldObj().getName()));
                    break;

            }
        }
    }

    private void getValue() {
        int ii = ll_list.getChildCount();
        if (ii > 0) {
            for (int i = 0; i < ii; i++) {
                View view = ll_list.getChildAt(i);
                if (view instanceof SingleTextView) {
                    String key = ((SingleTextView) view).getKey();
                    String value = ((SingleTextView) view).getValue();
                    for (ColumnsJsonBean b : datas) {
                        if (b.getDataFieldObj().getName().equals(key) && b.getRequiredObj().getValue() == 1) {//组件是同一个&& 必填
                            if (value.length() == 0) {
                                ToastUtil.toast(getBaseContext(), "请填写" + b.getHeaderText());
                                return;
                            }
                        }
                    }
                    map.put(key, value);
                }
            }
        }

        String value = WorkOrderDataManager.newInstance().getValueById(id);
        Gson gson = new Gson();
        List<Map<String, String>> list = gson.fromJson(value, new TypeToken<List<Map<String, String>>>() {
        }.getType());
        //删除指定游标位置上的集合，再添加新的集合在 该游标位置上
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
