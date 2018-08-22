package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.Section;
import com.wisesignsoft.OperationManagement.ui.activity.OrderSolvedActivity;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class WorkOrderDetailView extends LinearLayout {

    private LinearLayout ll_work_order_detail;
    /*存储所有的view*/
    private Map<String, View> views = new HashMap<>();
    /*只是为了台账选择组件,存储id和attrName的对应关系*/
    private Map<String, String> views2 = new HashMap<>();
    private List<Map<String, Object>> links;

    public WorkOrderDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.work_order_detail, this, true);
        ll_work_order_detail = (LinearLayout) view.findViewById(R.id.ll_work_order_detail);
    }

    /**
     * @param datas
     * @param context
     */
    public void setData(List datas, Context context) {
        LogUtil.log("");
        Realm realm = Realm.getDefaultInstance();
        for (final Object o : datas) {
            if (o instanceof Section) {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        Section section = new Section();
                        section.setID(((Section) o).getID());
                        section.setCurrent(((Section) o).isCurrent());
                        section.setLabel(((Section) o).getLabel());
                        section.setSection(((Section) o).getSection());
                        realm.copyToRealmOrUpdate(section);
                    }
                });
            }
        }

        RealmResults<Section> sectionRealmList = realm.where(Section.class).findAll();
        for (Section section : sectionRealmList) {
            LogUtil.log(section.getLabel() + "========" + section.getSection().size());
        }
        realm.close();
    }
}
