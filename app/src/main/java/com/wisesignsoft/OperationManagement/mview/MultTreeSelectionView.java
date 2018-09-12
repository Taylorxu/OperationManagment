package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.adapter.MultTreeSelectionAdapter;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.EventClassificationActivity;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import io.realm.ObjectChangeSet;
import io.realm.RealmObjectChangeListener;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 树形选择组件 多选
 */

public class MultTreeSelectionView extends RelativeLayout implements RealmObjectChangeListener<WorkOrder> {
    private LoadingView loadingView;
    private WorkOrder wo;
    private List<DictDatasBean> list1;
    TextView tv_title;
    private RecyclerView rv_mult;
    private MultTreeSelectionAdapter adapter;
    List<EventClassificationModel> datas; ////整理后的数据集
    List<EventClassificationModel> dataForAdapter;

    public MultTreeSelectionView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.mult_depart_choose, this, true);
        rv_mult = (RecyclerView) view.findViewById(R.id.rv_mult_content);
        RecyclerView.LayoutManager manager = new GridLayoutManager(context, 5);
        rv_mult.setLayoutManager(manager);
        adapter = new MultTreeSelectionAdapter(getContext(), null);
        rv_mult.setAdapter(adapter);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        adapter.setOnIMultChooseListener(new MultTreeSelectionAdapter.IMultChoose() {
            @Override
            public void setOnIMultChoose(EventClassificationModel bean) {
                if (wo.isModified()) {
                    dataForAdapter.remove(bean);
                    adapter.notifyDataSetChanged();//删除某个后更新adapter
                    StringBuffer sb = new StringBuffer();
                    for (int i = 0; i < dataForAdapter.size() - 1; i++) {
                        String id = dataForAdapter.get(i).getDictId();
                        if (i == dataForAdapter.size() - 2) {
                            sb.append(id);
                        } else {
                            sb.append(id).append(",");
                        }
                    }
                    removeFromDatas(bean.getDictId(), datas);
                    wo.setValue(sb.toString());
                    WorkOrderDataManager.newInstance().modifyValue(wo.getID(), sb.toString());

                }
            }

            @Override
            public void setOnAddClick() {
                if (wo.isModified()) {
                    EventClassificationActivity.startSelf(getContext(), datas, wo.getID(), wo.isMult());
                }
            }
        });
    }

    public void setData(final WorkOrder wo) {
        this.wo = wo;
        wo.addChangeListener(this);
        String title = wo.getName();
        final String content = wo.getValue();
        final String param = wo.getSrclib();

        if (!TextUtils.isEmpty(title)) {
            if (wo.isRequired()) {
                tv_title.setText(title + " *");
            } else {
                tv_title.setText(title);
            }
        }

        queryValidCiByModelName(param, content);//为控件赋值
    }

    /**
     * 查询单个字典有效数据
     *
     * @param param
     * @param content
     */
    private void queryValidCiByModelName(String param, final String content) {
        List<String> list = new ArrayList<>();
        list.add(param);
        loadingView = LoadingView.getLoadingView(getContext());
        loadingView.show();
        ApiService.Creator.get().queryValidCiByModelName(RequestBody.getgEnvelope(Protocol.dict_name_space, list, Protocol.queryValidCiByModelName))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<DictDatas>>())
                .flatMap(new FlatMapTopRes<DictDatas>(DataTypeSelector.RE))
                .subscribe(new Subscriber<DictDatas>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingView.stop(loadingView);
                        e.printStackTrace();
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                    }

                    @Override
                    public void onNext(DictDatas dictDatasBeans) {
                        list1 = dictDatasBeans.getDictDatas();
                        reNewDataForAdapter(content);
                        datas = pickOutRootData();               //整理后的数据集,for 选择界面
                        MultTreeSelectionAdapter.setDatas(dataForAdapter);
                        adapter.notifyDataSetChanged();
                        loadingView.stop(loadingView);
                    }
                });


    }

    /**
     * 根据workorder的value值
     */
    private void reNewDataForAdapter(String content) {
        List<EventClassificationModel> list = new ArrayList<>();
        if (!TextUtils.isEmpty(content)) {
            String[] strings;
            if (content.indexOf(",") > -1) {
                strings = content.split(",");
            } else {
                strings = new String[]{content};
            }
            if (list1 != null) {
                for (String id : strings) {
                    for (DictDatasBean bean : list1) {
                        if (bean.getDictId().equals(id)) {
                            bean.setSelected(true);
                            list.add(newEventClassificationModel(bean));
                        }
                    }
                }
            }
        }
        list.add(null);
        dataForAdapter = list;
    }

    /**
     * 根据数据整理出 父节点
     *
     * @return
     */
    private List<EventClassificationModel> pickOutRootData() {
        List<EventClassificationModel> datas = new ArrayList<>();
        for (DictDatasBean bean : list1) {
            if ("DICT_ROOT".equals(bean.getDictParentValue())) {
                datas.add(newEventClassificationModel(bean));
            }
        }
        fillChildrenList(datas);
        return datas;
    }

    /**
     * 填充父节点 下的 子节点
     * 可能的话，填充子节点下的子节点
     *
     * @param datas
     */
    public void fillChildrenList(List<EventClassificationModel> datas) {
        for (EventClassificationModel model : datas) {
            arrangeChilder(model);
        }
        for (EventClassificationModel model : datas) {
            for (EventClassificationModel model1 : model.getList()) {
                arrangeChilder(model1);
            }
        }
    }

    private void arrangeChilder(EventClassificationModel model) {
        List<EventClassificationModel> datas1 = new ArrayList<>();
        for (DictDatasBean bean : list1) {
            if (model.getDictValue().equals(bean.getDictParentValue())) {
                datas1.add(newEventClassificationModel(bean));
            }
        }
        model.setList(datas1);
    }

    private EventClassificationModel newEventClassificationModel(DictDatasBean bean) {
        EventClassificationModel model1 = new EventClassificationModel();
        model1.setDictName(bean.getDictName());
        model1.setDictId(bean.getDictId());
        model1.setDictParentValue(bean.getDictParentValue());
        model1.setDictValue(bean.getDictValue());
        model1.setSelected(bean.isSelected());
        return model1;
    }

    /**
     * 删除时 datas 里同步
     *
     * @param id
     */
    private void removeFromDatas(String id, List<EventClassificationModel> datas) {
        for (EventClassificationModel model : datas) {
            if (model.getDictId().equals(id)) {
                model.setSelected(false);
                return;
            }
            if (model.getList().size() > 0) {
                removeFromDatas(id, model.getList());
            }
        }
    }


    @Override
    public void onChange(WorkOrder workOrder, @Nullable ObjectChangeSet changeSet) {
        if (changeSet.isDeleted()) {
            return;
        }
        LogUtil.log(workOrder.getViewName() + "组件的value被改成" + workOrder.getValue());
        setData(workOrder);
        WorkOrderDataManager.newInstance().setValueForLinkWorkOrder(workOrder);
    }
}
