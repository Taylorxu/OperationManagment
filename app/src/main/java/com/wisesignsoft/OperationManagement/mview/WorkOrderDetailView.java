package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.Section;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.ui.activity.OrderSolvedActivity;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class WorkOrderDetailView extends LinearLayout {
    //最外层的布局
    public static LinearLayout ll_work_order_detail;

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
        Realm realm = Realm.getDefaultInstance();
        for (final Object o : datas) {
            if (o instanceof Section) {
                realm.executeTransaction(new Realm.Transaction() {
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
        //Section & WorkOrder
        RealmResults<Section> sectionRealmList = realm.where(Section.class).findAll();
        for (Section section : sectionRealmList) {
            SectionView sectionView = new SectionView(context);
            sectionView.setData(section.getLabel(), section.isCurrent());
            ll_work_order_detail.addView(sectionView);
            for (WorkOrder wo : section.getSection()) {
                if (!wo.isVisible() && !wo.getViewName().equals("Position")) {
                    continue;
                }
                String viewName = wo.getViewName();
                createCompoent(viewName, context, wo, sectionView);
            }
        }
        realm.close();
    }


    /**
     * 根据 条件 渲染组件
     */
    private void createCompoent(String viewName, Context context, WorkOrder wo, SectionView sectionView) {
        switch (viewName) {
            case "TextInput":   //输入框
                SingleTextView singleTextView = new SingleTextView(context);

                sectionView.getLl_section_view().addView(singleTextView);
                singleTextView.setData(wo);
                break;
            case "TextArea":    //文本域
                TextFieldView textFieldView = new TextFieldView(context);

                textFieldView.setData(wo);
                sectionView.getLl_section_view().addView(textFieldView);
                break;
            case "DataDisplayDate":     //时间显示组件
                DataDisplayDateView dataDisplayDateView = new DataDisplayDateView(context);
                sectionView.getLl_section_view().addView(dataDisplayDateView);
                dataDisplayDateView.setData(wo);
                break;
            case "DateFeild":   //时间日期选择组件
                TimeView timeView = new TimeView(context);
                sectionView.getLl_section_view().addView(timeView);
                timeView.setData(wo);
                break;
            case "TreeData":    //树形数据选择组件
                if (wo.isMult()) {
                    MultTreeSelectionView view = new MultTreeSelectionView(context);
                    sectionView.getLl_section_view().addView(view);
                    view.setData(wo);
                } else {
                    TreeSelectionView treeSelectionView = new TreeSelectionView(context);
                    sectionView.getLl_section_view().addView(treeSelectionView);
                    treeSelectionView.setData(wo);
                }

                break;
        }
    }

}
