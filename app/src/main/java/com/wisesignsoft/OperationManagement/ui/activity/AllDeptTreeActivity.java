package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.AllDeptTreeAdapter;
import com.wisesignsoft.OperationManagement.bean.Children;
import com.wisesignsoft.OperationManagement.mview.EmptyView;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.utils.LogUtil;
import com.wisesignsoft.OperationManagement.utils.TempTreeSelectionDataManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AllDeptTreeActivity extends BaseActivity implements AllDeptTreeAdapter.IAllDeptTreeListener, AllDeptTreeAdapter.MultableCheckListener {

    private MyTitle mt_all_dept_tree;
    private RecyclerView rv;
    private EmptyView empty;
    private List<Children> datas;
    private String id;
    private static boolean hasMult;

    public static void startSelf(Context context, List<Children> datas, String id, boolean hasMult) {
        Intent intent = new Intent();
        intent.setClass(context, AllDeptTreeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", (Serializable) datas);
        intent.putExtras(bundle);
        intent.putExtra("id", id);
        intent.putExtra("hasMult", hasMult);
        context.startActivity(intent);
    }

    public static void startSelf(Context context, List<Children> datas, String id) {
        Intent intent = new Intent();
        intent.setClass(context, AllDeptTreeActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", (Serializable) datas);
        intent.putExtras(bundle);
        intent.putExtra("hasMult", hasMult);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_dept_tree);
        TempTreeSelectionDataManager.getManager().addAllDeptTreeActivity(this);
        init();
        LogUtil.log("返回");
        request();
    }

    private void init() {
        /*获取控件id*/
        id = getIntent().getStringExtra("id");
        hasMult = getIntent().getBooleanExtra("hasMult", false);
        mt_all_dept_tree = (MyTitle) findViewById(R.id.mt_all_dept_tree);
        rv = (RecyclerView) findViewById(R.id.rv_all_dept_tree);
        empty = (EmptyView) findViewById(R.id.ev_all_dept_tree);
        setEnsureButton(hasMult);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);

    }


    static List<String> departNameList = new ArrayList<>();

    private void request() {
        if (datas != null && datas.size() > 0) datas.clear();
        datas = (List<Children>) getIntent().getSerializableExtra("datas");

        for (int i = 0; i < datas.size(); i++) {
            String id = datas.get(i).getDeptId();
            if (id.equals("root")) break;
            if (datas.get(i).isSelected()) {
                if (departNameList.size() > 0) {
                    if (!departNameList.contains(id)) {
                        departNameList.add(id);
                    }
                } else {
                    departNameList.add(id);
                }
            } else {
                if (departNameList.size() > 0) {
                    for (int o = 0; o < departNameList.size(); o++) {
                        if (departNameList.get(o).equals(id)) departNameList.remove(id);
                    }
                }
            }
        }
        setEmpty();
        initData();
    }


    private void initData() {
        AllDeptTreeAdapter allDeptTreeAdapter = new AllDeptTreeAdapter(this, datas, id, hasMult);
        rv.setAdapter(allDeptTreeAdapter);
        allDeptTreeAdapter.setOnIClick(this);
        allDeptTreeAdapter.setMultableCheckClick(this);
    }

    private void setEmpty() {
        if (datas == null || datas.size() == 0) {
            empty.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
        } else {
            empty.setVisibility(View.GONE);
            rv.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TempTreeSelectionDataManager.getManager().removeAllDeptTreeActivity(this);
    }

    @Override
    public void setOnIAllDeptTreeClick(String name) {
//        WorkOrderDataManager.getManager().setSingleDateById(id, name);
    }

    @Override
    public void setMultableCheckClick(Children deptChildren) {
        if (deptChildren.isSelected()) {
            departNameList.add(deptChildren.getDeptId());
        } else {
            departNameList.remove(deptChildren.getDeptId());
        }
    }

    public void setEnsureButton(Boolean isShow) {
        mt_all_dept_tree.setTvRight(isShow, getString(R.string.sure), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (departNameList.size() > 0) {
                    String result = departNameList.toString();
                    result = result.substring(1, result.length() - 1).replace(" ", "");
                    TempTreeSelectionDataManager.getManager().setTemp(result);
                    TempTreeSelectionDataManager.getManager().clearAllDeptTreeActivity();
//                    WorkOrderDataManager.getManager().setSingleDateById(id, result);
                }

            }
        });

    }

}
