package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wisesignsoft.OperationManagement.utils.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.LinkConditionData;
import com.wisesignsoft.OperationManagement.bean.Section;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class WorkOrderDetailView extends LinearLayout {
    private Context context;
    public static LinearLayout ll_work_order_detail;    //最外层的布局

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
     * 提交到下一环节时，下一环节的处理人还是上一环节处理人；则更新上一环节为已处理过环节，下一环节为当前环节
     */
    public void addOrReplaceChildView(String label, boolean isCurrent, SectionView newSectionView, int size) {
        if (ll_work_order_detail.getChildCount() == size) {
            for (int i = 0; i < ll_work_order_detail.getChildCount(); i++) {
                SectionView oldSectionView = (SectionView) ll_work_order_detail.getChildAt(i);
                List list = oldSectionView.getLabelandVisible();
                boolean oldOneisCurrent = (int) list.get(1) == View.VISIBLE ? true : false;
                if (label.equals(list.get(0)) && isCurrent != oldOneisCurrent) {//环节名称相等,但是是当前环节条件不一致。以最新的数据为准
                    ll_work_order_detail.removeViewAt(i);
                    ll_work_order_detail.addView(newSectionView, i);
                }
            }
        } else {
            ll_work_order_detail.addView(newSectionView);
        }
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
            addOrReplaceChildView(section.getLabel(), section.isCurrent(), sectionView, sectionRealmList.size());
            RealmList<WorkOrder> workOrders = section.getSection();
            for (WorkOrder wo : workOrders) {
                if (!wo.isVisible() && !wo.getViewName().equals("Position")) {
                    continue;
                }
                String viewName = wo.getViewName();
                createComponent(viewName, context, wo, sectionView);
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
        if (realm.isInTransaction()) realm.cancelTransaction();
        //先删除存在section在更新。 曾考虑过创建一个关联的对象，以pikey 为索引。但考虑到数据的更新频率过快，所以删除旧的section 只保留当前查看的数据。也减少了后期的数据管理
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Section> sectionResult = realm.where(Section.class).findAll();
                RealmResults<WorkOrder> workOrderResult = realm.where(WorkOrder.class).findAll();
                RealmResults<BMForm> bmFormsResult = realm.where(BMForm.class).findAll();
                RealmResults<LinkConditionData> conditionDataResults = realm.where(LinkConditionData.class).findAll();
                sectionResult.deleteAllFromRealm();
                workOrderResult.deleteAllFromRealm();
                bmFormsResult.deleteAllFromRealm();
                conditionDataResults.deleteAllFromRealm();
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
                if (!TextUtils.isEmpty(str)) {
                    Gson gson = new Gson();
                    List<Map<String, Object>> links = gson.fromJson(str, new TypeToken<List<Map<String, Object>>>() {
                    }.getType());
                    refreshLinkConditionData(realm, links);
                }
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
    private void createComponent(String viewName, Context context, WorkOrder wo, SectionView sectionView) {
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
            case "ResModelSelect":  //模型数据选择组件
                ResModelSelectView resModelSelectView = new ResModelSelectView(context);
                sectionView.getLl_section_view().addView(resModelSelectView);
                resModelSelectView.setData(wo);
                break;
            case "RadioButtons":    //单选钮
                SingleSelectView singleSelectView = new SingleSelectView(context);
                sectionView.getLl_section_view().addView(singleSelectView);
                singleSelectView.setData(wo);
                break;
            case "CheckBox"://复选框
            case "MultList"://多选框
                CheckBoxView checkBoxView = new CheckBoxView(context);
                sectionView.getLl_section_view().addView(checkBoxView);
                checkBoxView.setData(wo);
                break;
            case "SingleUserChoose":
            case "PersonInfo":      //单选人员组件
                SingleUserChooseView singleUserChooseView = new SingleUserChooseView(context);
                sectionView.getLl_section_view().addView(singleUserChooseView);
                singleUserChooseView.setDate(wo);
                break;
            case "MultUserChoose":      //多选人员列表组件
                MultUserChooseView multUserChooseView = new MultUserChooseView(context);
                sectionView.getLl_section_view().addView(multUserChooseView);
                multUserChooseView.setData(wo);
                break;
            case "DeptChoose":      //部门组件
                DeptChooseView deptChooseView = new DeptChooseView(context);
                sectionView.getLl_section_view().addView(deptChooseView);
                deptChooseView.setData(wo);
                break;
            case "SingleRoleChoose"://角色组件
                if (wo.isMult()) {
                    MultRoleChooseView multRoleChooseView = new MultRoleChooseView(context);
                    sectionView.getLl_section_view().addView(multRoleChooseView);
                    multRoleChooseView.setData(wo);
                } else {
                    SingleRoleChooseView singleRoleChooseView = new SingleRoleChooseView(context);
                    sectionView.getLl_section_view().addView(singleRoleChooseView);
                    singleRoleChooseView.setData(wo);
                }
                break;
            case "DynamicDataGrid":     //（台账）列表组件
                MultSelectListView multSelectListView = new MultSelectListView(context);
                sectionView.getLl_section_view().addView(multSelectListView);
                multSelectListView.setData(wo);
                break;
            case "ResDynamicDataGrid":       //模型数据列表组件，台賬
                MultSelectResModelView multSelectResModelView = new MultSelectResModelView(context);
                sectionView.getLl_section_view().addView(multSelectResModelView);
                multSelectResModelView.setData(wo);
                break;
            case "ConfigureChage":      //配置选择组件,單選
                ConfigureChangeView configureChangeView = new ConfigureChangeView(context);
                sectionView.getLl_section_view().addView(configureChangeView);
                configureChangeView.setData(wo);
                break;
            case "ResModelComponents"://配置项列表组件，多選配置項
                MultSelectConfigureChangeView multSelectConfigureChangeView = new MultSelectConfigureChangeView(context);
                sectionView.getLl_section_view().addView(multSelectConfigureChangeView);
                multSelectConfigureChangeView.setData(wo);
                break;
        }
    }

}
