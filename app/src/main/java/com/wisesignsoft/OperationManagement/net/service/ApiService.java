package com.wisesignsoft.OperationManagement.net.service;


import com.wisesignsoft.OperationManagement.Protocol;
import com.wisesignsoft.OperationManagement.bean.AccountInfoBean;
import com.wisesignsoft.OperationManagement.bean.DictDatas;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.bean.ResourcesBean;
import com.wisesignsoft.OperationManagement.bean.TaskDetailBean;
import com.wisesignsoft.OperationManagement.bean.TaskItemBean;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.net.response.BaseDataResponse;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;


/**
 * soap 的请求接口发起
 * Created by xuzhiguang on 2018/2/1.
 */

public interface ApiService {

    @POST(Protocol.yxyw_host)
    Observable<Response<BaseDataResponse<User>>> login(@Body String requestStr);

    @POST(Protocol.yxyw_host)
    Observable<Response<BaseDataResponse<List<ResourcesBean>>>> queryUserResource(@Body String requestStr);

    @POST(Protocol.yxyw_host)
    Observable<Response<BaseDataResponse<AccountInfoBean>>> queryValidUsersByAccount(@Body String requestStr);

    @POST(Protocol.yxyw_host)
    Observable<Response<BaseDataResponse<String>>> queryUnhandleProcessCount(@Body String requestStr);


    /**
     * 修改用户的闲忙状态
     *
     * @param requestStr
     * @return
     */
    @POST(Protocol.user_host)
    Call<String> updateUserSta(@Body String requestStr);

    /**
     * 获取待处理
     *
     * @param requestStr
     * @return
     */
    @POST(Protocol.yxyw_host)
    Observable<Response<BaseDataResponse<List<TaskItemBean>>>> findUnhandleProcess(@Body String requestStr);

    /**
     * 工单流程详情请求
     *
     * @param requestStr
     * @return
     */
    @POST(Protocol.process_host)
    Observable<Response<TaskDetailBean>> openTaskDetail(@Body String requestStr);

    /**
     * 查询单个字典有效数据
     * @param requestStr
     * @return
     */
    @POST(Protocol.dict_host)
    Observable<Response<BaseDataResponse<DictDatas>>> queryValidCiByModelName(@Body String requestStr);

    /**
     * 全部字典数据
     * @param requestStr
     * @return
     */
    @POST(Protocol.dict_host)
    Observable<Response<BaseDataResponse<List<DictDatas>>>> queryAllValidDictData(@Body String requestStr);


    class Creator {
        private static Strategy strategy = new AnnotationStrategy();
        private static Serializer serializer = new Persister(strategy);
        private static ApiService apiService;

        public static ApiService get() {
            if (apiService == null) {
                create();
            }
            return apiService;
        }

        private static synchronized void create() {
            if (apiService == null) {
                apiService = getRetrofit().create(ApiService.class);
            }
        }

        private static Retrofit getRetrofit() {
            return new Retrofit.Builder()
                    .baseUrl(Protocol.getHostUrl())
                    .client(NetConfig.getInstance().getClient())
                    .addConverterFactory(new ToStringConverterFactory())
                    .addConverterFactory(GsonConverterFactory.create(NetConfig.getInstance().getGson()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
    }

}
