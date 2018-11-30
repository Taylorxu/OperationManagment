package com.wisesignsoft.OperationManagement.db;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TaskDetailBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.mview.LoadingView;
import com.wisesignsoft.OperationManagement.net.response.CustomSubscriber;
import com.wisesignsoft.OperationManagement.net.response.FlatMapResponse;
import com.wisesignsoft.OperationManagement.net.service.ApiService;
import com.wisesignsoft.OperationManagement.net.service.RequestBody;
import com.wisesignsoft.OperationManagement.ui.activity.CreateOrderActivity;
import com.wisesignsoft.OperationManagement.ui.activity.OrderSolvedActivity;
import com.wisesignsoft.OperationManagement.ui.activity.SelectNextStepUserActivity;
import com.wisesignsoft.OperationManagement.utils.GsonHelper;
import com.wisesignsoft.OperationManagement.utils.ToastUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 某些特定功能的请求被多个地方调用，统一处理
 */
public class PublicRequest {
    public static PublicRequest publicRequest = null;
    Context context;

    public static PublicRequest newInstance() {
        if (publicRequest == null) {
            publicRequest = new PublicRequest();
        }
        return publicRequest;
    }

    //删除草稿
    public void requestCancel(List<String> list, final MyCallBack<Integer> callBack) {

        ApiService.Creator.get().deleteProcessInfoOnFirst(RequestBody.getgEnvelope(Protocol.process_name_space,
                list, Protocol.deleteProcessInfoOnFirst))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<Integer>())
                .subscribe(new CustomSubscriber<Integer>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        callBack.onResponse(1);
                    }

                    @Override
                    public void onNext(Integer state) {
                        callBack.onResponse(0);
                    }
                });

    }

    //保存草稿
    public void requestSure(List<String> list, final MyCallBack<Integer> callBack) {

        ApiService.Creator.get().saveProcessSketch(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.saveProcessSketch))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<Integer>())
                .subscribe(new CustomSubscriber<Integer>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        callBack.onResponse(1);
                    }

                    @Override
                    public void onNext(Integer state) {
                        callBack.onResponse(0);
                    }
                });
    }

    public void submitTask(Context context,final MyCallBack<Integer> callBack) {
        if (!WorkOrderDataManager.newInstance().checkEmptyValue(context)) {
            callBack.onResponse(2);//只是dismiss loading
            return;
        } else {
            WorkOrderDataManager.newInstance().fillFormValue();
        }
        String result = GsonHelper.build().objectToJsonString(WorkOrderDataManager.parameterMap);
        List<String> list = new ArrayList<>(Arrays.asList(result, User.getUserFromRealm().getUsername()));
        ApiService.Creator.get().submitTask(RequestBody.getgEnvelope(Protocol.process_name_space, list, Protocol.submitTask))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .flatMap(new FlatMapResponse<Integer>())
                .subscribe(new CustomSubscriber<Integer>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        callBack.onResponse(1);//提交失败状态
                    }

                    @Override
                    public void onNext(Integer state) {
                        callBack.onResponse(state);
                    }
                });
    }


}
