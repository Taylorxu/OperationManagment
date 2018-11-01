package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.ButtonModel;
import com.wisesignsoft.OperationManagement.bean.ConditionJudgment;
import com.wisesignsoft.OperationManagement.bean.NextNode;
import com.wisesignsoft.OperationManagement.bean.StractgyBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.ui.activity.OrderSolvedActivity;
import com.wisesignsoft.OperationManagement.ui.activity.ReNewTemplateActivity;
import com.wisesignsoft.OperationManagement.ui.activity.SelectNextStepUserActivity;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;

import java.util.ArrayList;
import java.util.List;

public class ButtonView extends LinearLayout {

    private ButtonModel buttonModel;
    private LinearLayout ll_button_view;
    private List<NextNode> trueList = new ArrayList<>();

    public ButtonView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.button_view, this, true);
        ll_button_view = (LinearLayout) view.findViewById(R.id.ll_button_view);
    }

    public void setData(final ButtonModel buttonModel, final String conditionJudgment) {
        WorkOrderDataManager.newInstance().saveButtonModel(buttonModel);
        this.buttonModel = buttonModel;
        List<NextNode> list = buttonModel.getNextNode();
        for (NextNode node : list) {
            if ("true".equals(node.getIsDependCondition())) {//流程要依赖conditionJudgment
                trueList.add(node);
            } else {
                createNoteButtonView(node);                ////流程不依赖conditionJudgment
            }
        }
        if (trueList.size() == 1) {
            NextNode node = trueList.get(0);
            createNoteButtonView(node);
        } else if (trueList.size() > 1) {//多个按钮要依赖conditionJudgment，只添加一个按钮
            NoteButtonView noteButtonView = new NoteButtonView(getContext());
            ll_button_view.addView(noteButtonView);
            noteButtonView.setData("提交");
            noteButtonView.setBtOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    toNode(getNextNode(buttonModel, conditionJudgment));
                }
            });
        }
    }

    /**
     * 以事件单 为例子：
     * 二线申请环节-----数据中nextNode集合长度为2 ；同时最外层conditionJudgment "[{'value':'二线申请--二线处理','operation':'==','valueDesc':'二线申请','key':'OBJ_NSECH==yesorno:11644302-b6b7-4c95-b33b-fc3ef8961114','keyName':'是否需要二线协助','valueName':{'value':'yesorno:11644302-b6b7-4c95-b33b-fc3ef8961114','name':'是'}},{'value':'二线申请--事件完成','operation':'==','valueDesc':'不需要二线接入','key':'OBJ_NSECH==yesorno:bfb0d4b8-0f93-4c44-b124-b3a43e2c3798','keyName':'是否需要二线协助','valueName':{'value':'yesorno:bfb0d4b8-0f93-4c44-b124-b3a43e2c3798','name':'否'}}]"
     * <p>
     * 此情况 代码中处理成一个button（提交）。提交时流程走向依据conditionJudgment  和 该环节中的 conditionJudgment指定的某个组件的值。
     * 把key 字段分成数组（后面都称为keys），keys[0]是组件dmAttrName属性的值,找到相应的组件后，在把该组件的值和keys[1]比较
     * 条件相等，在根据conditionJudgment 中的value 去匹配相应的按钮（nextNode）
     * <p>
     * 组件所选的值=下一步的流程；conditionJudgment 中的value是每个nextNode（按钮）的名称
     * 所以提交时，会把相应的nextNode的数据作为参数提交上去
     *
     * @param button
     * @param con
     */
    public NextNode getNextNode(final ButtonModel button, String con) {
        List<NextNode> nodes = button.getNextNode();
        List<ConditionJudgment> conditionJudgment = new Gson().fromJson(con, new TypeToken<List<ConditionJudgment>>() {
        }.getType());
        for (ConditionJudgment c : conditionJudgment) {
            String keyStr = c.getKey();
            String valueName = c.getValue();
            String operation = c.getOperation();
            String[] keys = keyStr.split(operation);
            WorkOrder workOrder = WorkOrderDataManager.newInstance().getWorkOrder("dmAttrName", keys[0]);
            if (workOrder != null) {
                String valueW = workOrder.getValue();
                if (valueW.equals(keys[1])) {
                    for (NextNode node : nodes) {
                        if (valueName.equals(node.getName())) {
                            WorkOrderDataManager.newInstance().modifyButtonModel("value", GsonHelper.build().objectToJsonString(node));
                            return node;
                        }
                    }
                }
            }
        }
        return null;
    }


    /**
     * * "Button":{
     * "nextNode":Array[3],
     * "@ID":"submit"
     * },
     * buttonModel.getID()---@ID":"submit"
     *
     * @param nextNode
     */
    private void createNoteButtonView(final NextNode nextNode) {
        NoteButtonView noteButtonView = new NoteButtonView(getContext());
        ll_button_view.addView(noteButtonView);
        noteButtonView.setData(nextNode.getNameDesc());//button 名称
        final String value = GsonHelper.build().objectToJsonString(nextNode);
        noteButtonView.setBtOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                WorkOrderDataManager.newInstance().modifyButtonModel("value", value);
                toNode(nextNode);
            }
        });
    }

    private void toNode(NextNode nextNode) {
        String key = null, strategyValue = null;
        StractgyBean bean = null;
        StractgyBean.StrategyValueBean strategyValueBean = null;

        if (nextNode != null) {
            String strategy = nextNode.getTaskStrategy();
            bean = GsonHelper.build().getObjectByJson(strategy, StractgyBean.class);
            key = bean.getStrategyKey();
            strategyValue = bean.getStrategyValue();
            strategyValueBean = GsonHelper.build().getObjectByJson(strategyValue, StractgyBean.StrategyValueBean.class);
        }

        if (key == null) {
            key = "";
        }

        switch (key) {
            case "assignee":
                SelectNextStepUserActivity.startSelf(getContext(), strategyValueBean.getRoleId(), buttonModel.getID(), "assignee");
                break;
            default:
                if (getContext() instanceof OrderSolvedActivity) {
                    ((OrderSolvedActivity) getContext()).commit();
                } else if (getContext() instanceof ReNewTemplateActivity) {
                    ((ReNewTemplateActivity) getContext()).commit();
                }
        }

    }


}
