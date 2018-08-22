package com.wisesignsoft.OperationManagement.net.response;

import rx.Observable;
import rx.functions.Func1;

import static com.wisesignsoft.OperationManagement.net.response.DataTypeSelector.RE;
import static com.wisesignsoft.OperationManagement.net.response.DataTypeSelector.RS;


public class FlatMapTopRes<Data> implements Func1<BaseDataResponse<Data>, Observable<Data>> {
    private String dataType;


    public FlatMapTopRes(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public Observable<Data> call(BaseDataResponse<Data> response) {
        if ("0".equals(response.getReturnState())) {
            if (dataType.equals(RE)) {
                return Observable.just(response.getReturnValue());
            } else if (dataType.equals(RS)) {
                return Observable.just(response.getResources());
            }
            return Observable.just(response.getData());
        } else {
            return Observable.error(response.getError());
        }
    }
}
