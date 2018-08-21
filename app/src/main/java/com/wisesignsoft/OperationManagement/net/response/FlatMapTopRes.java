package com.wisesignsoft.OperationManagement.net.response;

import rx.Observable;
import rx.functions.Func1;


public class FlatMapTopRes<Data> implements Func1<BaseListDataResponse<Data>, Observable<Data>> {
    @Override
    public Observable<Data> call(BaseListDataResponse<Data> response) {
        if ("0".equals(response.getReturnState())) {
            return Observable.just(response.getData());
        } else {
            return Observable.error(response.getError());
        }

    }
}
