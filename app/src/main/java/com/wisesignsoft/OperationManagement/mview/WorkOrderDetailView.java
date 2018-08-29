package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.LinkConditionData;
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
    public void refreshRealmData(final List datas) {
        final Realm realm = Realm.getDefaultInstance();
        //先删除存在section在更新。 曾考虑过创建一个关联的对象，以pikey 为索引。但考虑到数据的更新频率过快，所以删除旧的section 只保留当前查看的数据。也减少了后期的数据管理
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Section> sectionRealmList = realm.where(Section.class).findAll();
                RealmResults<BMForm> bmForms = realm.where(BMForm.class).findAll();
                sectionRealmList.deleteAllFromRealm();
                bmForms.deleteAllFromRealm();
            }
        });
        for (final Object o : datas) {
            if (o instanceof BMForm) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        BMForm bmForm = ((BMForm) o);
                        realm.insert(bmForm);
                    }
                });
            } else if (o instanceof BMFormDataLinkage) {//控件的值之间存在互相影响的根据数据集
                String str = ((BMFormDataLinkage) o).getDataLinkageJson();
                Gson gson = new Gson();
                List<Map<String, Object>> links = gson.fromJson(str, new TypeToken<List<Map<String, Object>>>() {
                }.getType());
                refreshLinkConditionData(realm, links);
            } else if (o instanceof Section) {// 控件 数据集
                realm.executeTransaction(new Realm.Transaction() {
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
            }
        }
        realm.close();
        fillComponents();
    }

    /**
     * 保存 联动数据条件
     *
     * @param realm
     * @param links
     */
    private void refreshLinkConditionData(Realm realm, List<Map<String, Object>> links) {
        final List<LinkConditionData> conditionDataList = new ArrayList<>();
        for (int i = 0; i < links.size(); i++) {
            Map<String, Object> map = links.get(i);
            String woId = (String) map.get("ID");
            String methodName = (String) map.get("methodName");
            String expreDesc = (String) map.get("expreDesc");
            LinkConditionData linkConditionData = new LinkConditionData();
            linkConditionData.setId(i);
            linkConditionData.setWorkOrderId(woId);
            linkConditionData.setMethodName(methodName);
            linkConditionData.setExpreDesc(expreDesc);
            conditionDataList.add(linkConditionData);
        }
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(conditionDataList);
            }
        });
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
            case "DataDisplayUser":    //人员显示组件
                DataDisplayUserView dataDisplayUserView = new DataDisplayUserView(context);
                sectionView.getLl_section_view().addView(dataDisplayUserView);
                dataDisplayUserView.setData(wo);
                break;
            case "NumericStepper":  //数字组件
                NumberView numberView = new NumberView(context);
                sectionView.getLl_section_view().addView(numberView);
                numberView.setData(wo);
                break;
            case "Position"://工程师位置数据显示组件
                PositionView positionView = new PositionView(context);
                sectionView.getLl_section_view().addView(positionView);
                positionView.setData(wo);
                break;
            case "nextNode":
                NoteButtonView noteButtonView = new NoteButtonView(context);
                sectionView.getLl_section_view().addView(noteButtonView);
                break;

        }
    }

}
