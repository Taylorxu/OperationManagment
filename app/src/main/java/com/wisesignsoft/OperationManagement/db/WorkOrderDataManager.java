package com.wisesignsoft.OperationManagement.db;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.bean.BMForm;
import com.wisesignsoft.OperationManagement.bean.ButtonModel;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.LinkConditionData;
import com.wisesignsoft.OperationManagement.bean.LinkParameter;
import com.wisesignsoft.OperationManagement.bean.LinkServiceData;
import com.wisesignsoft.OperationManagement.bean.NextNode;
import com.wisesignsoft.OperationManagement.bean.ResModeValue;
import com.wisesignsoft.OperationManagement.bean.Section;
import com.wisesignsoft.OperationManagement.bean.TaskStrategy;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.realm.ProxyState;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WorkOrderDataManager {
    private static WorkOrderDataManager manager;
    public static Map<String, String> parameterMap = new HashMap<>();

    public static WorkOrderDataManager newInstance() {
        if (manager == null) {
            manager = new WorkOrderDataManager();
        }
        return manager;
    }

    /**
     * 根据ID 修改order的value
     *
     * @param orderId
     * @param newValue
     */
    public void modifyValue(final String orderId, final String newValue) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                WorkOrder workOrder = realm.where(WorkOrder.class).equalTo("ID", orderId).findFirst();
                workOrder.setValue(newValue);

            }
        });
        realm.close();
    }

    /**
     * 根据ID 获取order的value
     *
     * @param orderId
     */
    public String getValueById(final String orderId) {
        String returnValue;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        WorkOrder workOrder = realm.where(WorkOrder.class).equalTo("ID", orderId).findFirst();
        returnValue = workOrder.getValue();
        realm.commitTransaction();
        realm.close();
        return returnValue;
    }

    /**
     * 获取接口字典数据
     */
    public void getAllValidDictData() {

        ApiService.Creator.get().queryAllValidDictData(RequestBody.getgEnvelope(Protocol.dict_name_space, new ArrayList<String>(), Protocol.queryAllValidDictDate))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<List<DictDatas>>>())
                .flatMap(new FlatMapTopRes<List<DictDatas>>(DataTypeSelector.RE))
                .subscribe(new Subscriber<List<DictDatas>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(final List<DictDatas> dictDatas) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                refreshAllValidDictData(dictDatas);
                            }
                        }).start();
                    }


                });
    }

    /**
     * 更新realm的字典数据
     *
     * @param dictDatas
     */
    public void refreshAllValidDictData(final List<DictDatas> dictDatas) {

        final Realm realm = Realm.getDefaultInstance();
        for (int i = 0; i < dictDatas.size(); i++) {
            final int finalI = i;
            final DictDatas dic = dictDatas.get(finalI);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (dic.getDictDatas() != null) {
                        final List<DictDatasBean> dictDatasBeans = new ArrayList<>();
                        for (DictDatasBean dictDatasBean : dic.getDictDatas()) {
                            dictDatasBean.setSrclib(dic.getKey());
                            dictDatasBeans.add(dictDatasBean);
                        }
                        realm.insertOrUpdate(dictDatasBeans);
                    }
                }
            });
        }
        realm.close();
    }

    /**
     * 根据 srclib 字典模型编码 去获取对应的字典集合
     */
    public void getDictDatasBySrclib(final String srclib, final CallBack<List<DictDatasBean>> callBack) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<DictDatasBean> realmResults = realm.where(DictDatasBean.class).equalTo("srclib", srclib).findAll();
                if (realmResults.isLoaded()) {
                    List<DictDatasBean> beanList = Arrays.asList(realmResults.toArray(new DictDatasBean[]{}));
                    callBack.onResponse(beanList);
                }

            }
        });
        realm.close();

    }

    /**
     * 根据字典的ID 查找出对应的值
     *
     * @param value
     * @return
     */
    public void getDicValueById(final String value, final CallBack<String> callBack) {
        if (TextUtils.isEmpty(value)) {
            callBack.onResponse("");
            return;
        }
        Realm realm = Realm.getDefaultInstance();
        if (!realm.isInTransaction()) realm.beginTransaction();
        RealmQuery<DictDatasBean> realmQuery = realm.where(DictDatasBean.class);
        DictDatasBean dictDatasBean = realmQuery.equalTo("dictId", value).findFirst();
        if (dictDatasBean != null)
            callBack.onResponse(dictDatasBean.getDictName());
        if (realm.isInTransaction()) realm.commitTransaction();
        realm.close();
    }

    /**
     * 联动被触发后，寻找相应的控件并赋值
     * 会调用 {@link #getLinkServiceData(String, LinkConditionData)}
     *
     * @param trigger
     */
    public void setValueForLinkWorkOrder(final WorkOrder trigger) {
        Realm realm = Realm.getDefaultInstance();
        if (!realm.isInTransaction()) realm.beginTransaction();
        //根据WorkOrder。ID 查找出相应的要被改变值的控件dmAttrName
        RealmResults<LinkConditionData> results = realm.where(LinkConditionData.class).equalTo("workOrderId", trigger.getID()).findAll();
        BMForm bmForm = realm.where(BMForm.class).findFirst();
        realm.commitTransaction();
        realm.close();
        if (results.isLoaded() && results.size() > 0) {
            for (LinkConditionData data : results) {
                getLinkServiceData(bmForm.getBmModelName(), data);
            }
        }

    }

    /**
     * 获取联动数据
     *
     * @param bmModelName
     * @param conditionData
     * @see #getLinkServiceData(String, LinkConditionData) Map formValue 内容是获取整个界面上所有组件 {@link WorkOrder#dmAttrName} --->（key） 和 {@link WorkOrder#value}--->(value)的值（数据量很是庞大）
     * 但接口其实真正用到的数据与控件的联动表达式{@link WorkOrder#expreDesc } 是大致吻合的。
     * 例如，事件测试单 "应用影响" 下拉框 ，该workOrder 对应（根据{@link WorkOrder#ID }查找对应）的联动表达式如下：
     * <p>
     * {expreDesc=[
     * {
     * 'customerDmAttrName':'OBJ_SYSFVIP',
     * 'systemDmAttrName':'OBJ_SYXTJB',
     * 'useDmAttrName':'OBJ_SYYYYX',
     * 'conServerDmAttrName':'OBJ_SCA',
     * 'levelGradeDmAttrName':'OBJ_SLEVER',
     * 'solveTimeLimitDmAttrName':'OBJ_JFT',
     * 'getTimeDmAttrName':'OBJ_ATST',
     * 'objPriority':'OBJ_YXJA'
     * }
     * ] , mx_internal_uid=6F9123C3-4EDC-CF31-3852-5BB6E4CEE620, methodName=thingLinkLevelGrade, ID=combobox_29, methodDesc=事件流程首页 联动服务等级三个字段, nodeType=ComboBox, name=应用影响}
     * </>
     * <p>
     * ￥￥￥￥￥￥￥￥接口实际需要的工单部分数据参数 如下：
     * {@link LinkParameter#attrsMap}
     * "attrsMap":{
     * "OBJ_SYXTJB":"xtjb:5f268603-59db-4afe-a31b-9e726f17227f",
     * "OBJ_SRT":"SR_TABLE:b5a54f0b-7756-41cc-bbbc-b5f36cd5880f",
     * "OBJ_SYYYYX":"yyyx:4b16ccb7-16bb-4ede-b522-abe344571ec6",
     * "OBJ_SYSFVIP":"khlb:5c4cec68-0ea3-4fce-9005-913245393749"
     * }
     * OBJ_SRT 是 '项目选择'组件（关键词ResModelSelect）{@link WorkOrder#dmAttrName} 的值,显然不在上面的表达式里面，所以不能做到筛选只能全拿给后台。
     * <p>
     * 接口invokeDataLinkageMethod 请求的参数 链接 <hr>https://pan.baidu.com/s/1b6nRrJc41Q2Q-pGO3AqVWQ</> 密码: fw7b
     * 事件单详情数据链接:  <hr>https://pan.baidu.com/s/1Q55eAj-slHweRtVoXSKUKQ </> 密码: u5u3
     */
    private void getLinkServiceData(String bmModelName, LinkConditionData conditionData) {
        Map<String, String> formValue = getReturnStringModel();
        final Gson gson = new Gson();
        List<String> listData = new ArrayList<>();
        LinkParameter bean = new LinkParameter(bmModelName, conditionData.getExpreDesc(), formValue);
        String b = gson.toJson(bean);
        listData.add(conditionData.getMethodName());  //MethodName 对应arg0（后台拿参数的顺序） 不能变
        listData.add(b);
        ApiService.Creator.get().invokeDataLinkageMethod(RequestBody.getgEnvelope(Protocol.business_name_space, listData, Protocol.invokeDataLinkageMethod))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new FlatMapResponse<BaseDataResponse<String>>())
                .flatMap(new FlatMapTopRes<String>(DataTypeSelector.RE))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(final String response) {
                        if (!TextUtils.isEmpty(response)) {
                            List<LinkServiceData> datas = gson.fromJson(response, new TypeToken<List<LinkServiceData>>() {
                            }.getType());
                            findLinkWorkOrder(datas);
                        }
                    }
                });
    }

    /**
     * 根据invokeDataLinkageMethod 返回的数据去匹配相应的WorkOrder
     * 会调用 {@link #modifyWorkOrderKeyField(LinkServiceData, Realm)}
     *
     * @param datas
     */
    private void findLinkWorkOrder(List<LinkServiceData> datas) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for (LinkServiceData bean : datas) {
            modifyWorkOrderKeyField(bean, realm);
        }
        realm.commitTransaction();
        realm.close();
    }

    /**
     * 根据联动数据查找到workOrder ，修改相应的字段
     *
     * @param bean
     * @param realm
     */
    private void modifyWorkOrderKeyField(final LinkServiceData bean, Realm realm) {
        int type = bean.getControlType();
        String dmAttrName = bean.getDmAttrName();
        boolean hasEdit = bean.isHasEdit();
        boolean hasRequired = bean.isHasRequired();
        boolean hasVisble = bean.isHasVisible();
        String value = bean.getValue();

        WorkOrder wo = realm.where(WorkOrder.class).equalTo("dmAttrName", dmAttrName).findFirst();
        if (wo != null)
            switch (type) {
                case 1:
                    wo.setValue(value);
                    break;
                case 2:
                    break;
                case 3:
                    wo.setModified(hasEdit);
                    break;
                case 4:
                    wo.setVisible(hasVisble);
                    break;
                case 5:
                    wo.setRequired(hasRequired);
                    break;
            }

    }

    /**
     * 逻辑描述：先根据from找到需要赋值的控件，然后根据to匹配map的key取出对应的value，最后把value赋值到控件里
     * 有些组件的值是走此方式给其它组件赋值，而非依靠联动表达式。如 ‘项目选择’ ---客户编号 等组件需要走此方式
     */
    public void setResModelValueByFromOrTo(List<ResModeValue.ConfigValueJsonBean> configValueJson, Map<String, String> data) {
        Realm realm = Realm.getDefaultInstance();

        for (ResModeValue.ConfigValueJsonBean model : configValueJson) {
            String to = model.getToFmAttrName().getDmAttrName();
            final String from = model.getFromDmAttrName().getDmAttrName();
            final String value = data.get(to);
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    WorkOrder workOrder = realm.where(WorkOrder.class).equalTo("dmAttrName", from).findFirst();
                    workOrder.setValue(value);
                }
            });

        }

        realm.close();
    }

    /**
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public WorkOrder getWorkOrder(String fieldName, String fieldValue) {
        WorkOrder workOrder;
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        workOrder = realm.where(WorkOrder.class).equalTo(fieldName, fieldValue).findFirst();
        realm.commitTransaction();
        realm.close();
        return workOrder;
    }

    /**
     * @param callBack
     */
    public void queryAllWorkOrder(final CallBack<RealmResults<WorkOrder>> callBack) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<WorkOrder> results = realm.where(WorkOrder.class).findAll();
                if (results.isLoaded()) callBack.onResponse(results);
            }
        });
    }

    /**
     * save button data
     */
    public void saveButtonModel(final ButtonModel buttonModel) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(buttonModel);
            }
        });
    }

    /**
     * modify ButtonModel
     *
     * @param fieldName
     * @param fieldValue
     */
    public void modifyButtonModel(final String fieldName, final String fieldValue) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ButtonModel buttonModel = realm.where(ButtonModel.class).findFirst();
                if (fieldName.equals("value")) {
                    buttonModel.setValue(fieldValue);
                }
            }
        });
    }

    /**
     * 提交单子数据集
     *
     * @param mapInit
     */
    public void setMapInit(Map<String, String> mapInit) {
        this.parameterMap = mapInit;
    }

    /**
     * 获取整个工单的数据集
     *
     * @return
     */
    public Map<String, String> getReturnStringModel() {
        Realm realm = Realm.getDefaultInstance();
        final Map<String, String> dmAttrNameMap = new HashMap<>();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<WorkOrder> results = realm.where(WorkOrder.class).findAll();
                if (results.isLoaded())
                    for (WorkOrder wo : results) {
                        if (!TextUtils.isEmpty(wo.getDmAttrName())) {
                            dmAttrNameMap.put(wo.getDmAttrName(), wo.getValue());
                        }
                    }
            }
        });
        return dmAttrNameMap;
    }

    /**
     * 校验必填值
     *
     * @param context
     * @return
     */
    public boolean checkEmptyValue(final Context context) {
        final boolean[] ok = {true};
        queryAllWorkOrder(new CallBack<RealmResults<WorkOrder>>() {
            @Override
            public void onResponse(RealmResults<WorkOrder> workOrders) {
                for (WorkOrder wo : workOrders) {
                    if (wo.getName().equals("优先级")) {
                        LogUtil.log(wo.isRequired() + "-------------" + TextUtils.isEmpty(wo.getValue()));
                    }
                    if (wo.isRequired() && TextUtils.isEmpty(wo.getValue())) {
                        Toast.makeText(context, "请填写" + wo.getName(), Toast.LENGTH_SHORT).show();
                        ok[0] = false;
                        break;
                    }
                }
            }
        });
        return ok[0];
    }

    /**
     * 表单数据放到集合里，作为提交单接口参数
     */
    public void fillFormValue() {
        queryAllWorkOrder(new CallBack<RealmResults<WorkOrder>>() {
            @Override
            public void onResponse(RealmResults<WorkOrder> workOrders) {
                for (WorkOrder wo : workOrders) {
                    parameterMap.put(wo.getDmAttrName(), wo.getValue());
                }
            }
        });
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ButtonModel buttonModel = realm.where(ButtonModel.class).findFirst();
                String json = buttonModel.getValue();
                TaskStrategy taskStrategy = buttonModel.getTaskStrategy();
                Gson gson = new Gson();
                NextNode node = GsonHelper.build().getObjectByJson(json, NextNode.class);
                parameterMap.put("outCome", node.getName());
                parameterMap.put("outComeDesc", node.getNameDesc());
                parameterMap.put("specificValueUpdate", node.getSpecificValueUpdate());
                if (taskStrategy != null) {
                    parameterMap.put("taskStrategy", gson.toJson(taskStrategy));
                } else {
                    String temp = node.getTaskStrategy();
                    parameterMap.put("taskStrategy", temp);
                }
            }
        });

        LogUtil.log(parameterMap.toString());
    }

}
