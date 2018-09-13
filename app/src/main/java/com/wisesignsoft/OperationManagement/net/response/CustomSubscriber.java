package com.wisesignsoft.OperationManagement.net.response;

import com.wisesignsoft.OperationManagement.utils.EEMsgToastHelper;

import rx.Subscriber;

abstract public class CustomSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        EEMsgToastHelper.newInstance().selectWitch(e.getMessage());
    }

    @Override
    public void onNext(T t) {

    }
}
