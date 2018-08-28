package com.wisesignsoft.OperationManagement.db;

import android.text.TextUtils;

import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.WorkOrder;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;
import com.wisesignsoft.OperationManagement.net.response.DataTypeSelector;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.response.FlatMapTopRes;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;
import com.wisesignsoft.OperationManagement.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

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

    private static List<DictDatas> dictDatasBeanList;

    public static WorkOrderDataManager newInstance() {
        if (manager == null)
            manager = new WorkOrderDataManager();
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
     * 字典数据
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

    public void refreshAllValidDictData(final List<DictDatas> dictDatas) {

        final Realm realm = Realm.getDefaultInstance();
        for (int i = 0; i < dictDatas.size(); i++) {
            final int finalI = i;
            final DictDatas dic = dictDatas.get(finalI);
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    DictDatas dicNew = new DictDatas();
                    dicNew.setId(finalI);
                    dicNew.setKey(dic.getKey());
                    realm.insertOrUpdate(dicNew);
                }
            });

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (dic.getDictDatas() != null) {
                        final List<DictDatasBean> dictDatasBeans = new ArrayList<>();
                        for (DictDatasBean dictDatasBean : dic.getDictDatas()) {
                            dictDatasBeans.add(dictDatasBean);
                        }
                        realm.insertOrUpdate(dictDatasBeans);
                    }
                }
            });
        }

      /*  RealmResults<DictDatas> datas = realm.where(DictDatas.class).findAll();
        for (DictDatas datas1 : datas) {
            LogUtil.log(datas1.getKey() + "===");
        }*/
        RealmResults<DictDatasBean> dictDatasBeans = realm.where(DictDatasBean.class).findAll();
        for (DictDatasBean d : dictDatasBeans) {
            LogUtil.log(d.getDictName() + "++++" + d);
        }

        realm.close();
    }

    /**
     * 根据 srclib 字典模型编码 去获取对应的字典集合
     *
     * @param wo
     * @return
     */
    public RealmList<DictDatasBean> getDictDatas(final WorkOrder wo) {
        final DictDatas[] dictDatas = new DictDatas[1];
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                dictDatas[0] = realm.where(DictDatas.class).equalTo("key", wo.getSrclib()).findFirst();

            }
        });
        realm.close();
        return dictDatas[0].getDictDatas();

    }

    /**
     * 根据字典的ID 查找出对应的值
     *
     * @param value
     * @return
     */
    public void getDicValue(final String value, final CallBack<String> callBack) {
        if (TextUtils.isEmpty(value)) {
            callBack.onResponse("");
            return;
        }
        Realm realm = Realm.getDefaultInstance();
        if (!realm.isInTransaction()) realm.beginTransaction();
        RealmQuery<DictDatasBean> realmQuery = realm.where(DictDatasBean.class);
        DictDatasBean dictDatasBean = realmQuery.equalTo("dictId", value).findFirst();
        callBack.onResponse(dictDatasBean.getDictName());
        if (realm.isInTransaction()) realm.commitTransaction();
        realm.close();
    }


}
