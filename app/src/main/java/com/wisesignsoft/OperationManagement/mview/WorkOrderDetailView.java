package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.Section;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.OrderSolvedActivity;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObjectChangeListener;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkOrderDetailView extends LinearLayout {
    //最外层的布局
    public static LinearLayout ll_work_order_detail;
    private Context context;

    public WorkOrderDetailView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        View view = LayoutInflater.from(context).inflate(R.layout.work_order_detail, this, true);
        ll_work_order_detail = (LinearLayout) view.findViewById(R.id.ll_work_order_detail);
    }

    /**
     * 根据更新后的ream数据去填充布局
     */
    public void fillComponents() {
        Realm realm = Realm.getDefaultInstance();
        //Section & WorkOrder
        RealmResults<Section> sectionRealmList = realm.where(Section.class).sort("ID").findAll();
        for (Section section : sectionRealmList) {
            SectionView sectionView = new SectionView(context);
            LogUtil.log(section.getLabel()+"section.getLabel");
            sectionView.setData(section.getLabel(), section.isCurrent());
            ll_work_order_detail.addView(sectionView);
            RealmList<WorkOrder> workOrders = section.getSection();
            for (WorkOrder wo : workOrders) {
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
     * 根据接口返回的数据
     *
     * @param datas
     */
    public void refreshRealmData(List datas) {
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
                        RealmList<WorkOrder> workOrders = new RealmList<>();
                        for (WorkOrder workOrder : ((Section) o).getSection()) {
                            workOrders.add(workOrder);
                        }
                        section.setSection(workOrders);
                        realm.copyToRealmOrUpdate(section);
                    }
                });

            } else {
                //TODO 其它
            }
        }
        realm.close();
        fillComponents();
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
            case "ComboBox":    //组合框
                BottomView bottomView = new BottomView(context);
                sectionView.getLl_section_view().addView(bottomView);
                bottomView.setData(wo);
                break;
        }
    }

}
