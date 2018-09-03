package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.Dict;
import com.wisesignsoft.OperationManagement.bean.ResColumnsJsonBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.ListActivity3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 添加台账列表
 * Created by ycs on 2016/12/17.
 */

public class MultSelectResModelView extends ListDateView implements ListDateView.IListDateClickListener {
    private static WorkOrder wo;
    private static ArrayList<ResColumnsJsonBean> datas;

    public MultSelectResModelView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {

        initView(context, R.mipmap.taizhang, "添加台账");
        setIListDataClickListener(this);
    }

    public void setData(WorkOrder wo) {
        MultSelectResModelView.wo = wo;
        setTitle(wo.getName());
        hideAddBtn(wo.isModified());
        String columnsJson = wo.getColumnsJson();
        Gson gson = new Gson();
        datas = gson.fromJson(columnsJson, new TypeToken<List<ResColumnsJsonBean>>() {
        }.getType());
        String str = wo.getValue();
        List<Map<String, Dict>> list;
        clearView();
        if (!TextUtils.isEmpty(str)) {
            list = gson.fromJson(str, new TypeToken<List<Map<String, Dict>>>() {
            }.getType());
            for (int i = 0; i < list.size(); i++) {
                StandBookView standBookView = new StandBookView(getContext());
                addStandBook(standBookView);
                standBookView.setDeletePosition(i);
                standBookView.setDeleteHide(wo.isModified());
                standBookView.setData3(getContext(), datas, list.get(i), 3, wo);
            }
            setGridListShowOrHide(true);
        }
    }

    @Override
    public void setOnAddView() {
        Gson gson = new Gson();
        String str = wo.getValue();
        List<Map<String, Dict>> list = gson.fromJson(str, new TypeToken<List<Map<String, Dict>>>() {
        }.getType());
        if (list == null) {
            list = new ArrayList<>();
        }
        Map<String, Dict> map = new HashMap<>();
        StandBookView standBookView = new StandBookView(getContext());
        standBookView.setDeletePosition(list == null ? 0 : list.size());
        ListActivity3.startSelf(getContext(), map, datas, wo, list == null ? 0 : list.size());
    }


    /**
     * 摸型数据选择组件  在摸型组件列表中，选择一个返回其列 和对应的数据
     * 列名称的字段是 map的key ,该key与ResColumnsJsonBean下的DataFieldObjBean 的value属性值一致
     * 该方法目的是 将选择好的组件值 转换成一个数组格式的字符串给 ResDynamicDataGrid 的value
     * 在根据workerOrder id 进行刷新
     * 这个方法有被调用！！！
     */

    public void addViewFromElect(Map<String, String> columnsMap) {
        String str = wo.getValue();
        List<Map<String, Dict>> list;
        if (!TextUtils.isEmpty(str)) {//如果，在选择现有的组件之前已经存在数据
            list = new Gson().fromJson(str, new TypeToken<List<Map<String, Dict>>>() {
            }.getType());
        } else {
            list = new ArrayList<>();
        }
        Map<String, Dict> dictMap = new HashMap<>();
        for (ResColumnsJsonBean bean : datas) {
            String matchKey = bean.getDataFieldObj().getValue();
            if (columnsMap.containsKey(matchKey)) {
                Dict dict = new Dict();
                dict.setKey(matchKey);
                dict.setValue(columnsMap.get(matchKey));
                dict.setName(columnsMap.get(matchKey));
                dictMap.put(matchKey, dict);
            }
        }
        list.add(dictMap);
        WorkOrderDataManager.newInstance().modifyValue(wo.getID(), new Gson().toJson(list));
        StandBookView standBookView = new StandBookView(getContext());
        standBookView.setDeletePosition(list.size() - 1);
        addStandBook(standBookView);
        standBookView.setData3(getContext(), datas, dictMap, 3, wo);
        setGridListShowOrHide(true);
    }

    @Override
    public void setOnDelView(StandBookView standBookView) {
        Gson gson = new Gson();
        String str = wo.getValue();
        List<Map<String, Dict>> list = gson.fromJson(str, new TypeToken<List<Map<String, Dict>>>() {
        }.getType());
        if (list.size() > 0) {
            list.remove(standBookView.getDeletePosition());
            wo.setValue(list.toString());
            String temp = gson.toJson(list);
            WorkOrderDataManager.newInstance().modifyValue(wo.getID(), temp);
        }

    }
}
