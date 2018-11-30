package com.wisesignsoft.OperationManagement.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.ButtonModel;
import com.wisesignsoft.OperationManagement.bean.NextNode;
import com.wisesignsoft.OperationManagement.mview.ButtonView;

import java.util.List;

import io.realm.Realm;

public abstract class OrderDetailRootActivity extends AppCompatActivity {
    private BMForm bmForm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Realm.getDefaultInstance().removeAllChangeListeners();
    }


    public void setButton(LinearLayout ll_new_temp, List datas) {
        if (ll_new_temp.getChildCount() > 1) ll_new_temp.removeViewAt(1);
        for (Object o : datas) {
            if (o instanceof ButtonModel) {
                List<NextNode> list = ((ButtonModel) o).getNextNode();
                if (list == null || list.size() == 0) {
                    continue;
                }
                ButtonView buttonView = new ButtonView(this);
                buttonView.setData((ButtonModel) o, bmForm.getConditionJudgment());
                ll_new_temp.addView(buttonView);
            } else if (o instanceof BMForm) {
                bmForm = ((BMForm) o);
            }
        }

    }

    public abstract void setButton();
}
