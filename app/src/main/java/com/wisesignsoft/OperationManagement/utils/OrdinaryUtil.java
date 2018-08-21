package com.wisesignsoft.OperationManagement.utils;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.OrdinaryModel;
import com.wisesignsoft.OperationManagement.bean.User;
import com.wisesignsoft.OperationManagement.db.MySharedpreferences;
import com.wisesignsoft.OperationManagement.net.response.QueryUserResourceResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ycs on 2016/12/5.
 */

public class OrdinaryUtil {
    public static List<OrdinaryModel> toOrdinaryModel(List<QueryUserResourceResponse.ResourcesBean> datas) {
        List<OrdinaryModel> models = new ArrayList<>();
        for (QueryUserResourceResponse.ResourcesBean bean : datas) {
            int reId = getReIdByUrl(bean.getResUrl());
            if (-1 != reId) {
                OrdinaryModel model = new OrdinaryModel();
                model.setName(bean.getResName());
                model.setReId(reId);
                model.setResUrl(bean.getResUrl());
                model.setResPar(bean.getResPar());
                models.add(model);
            }
        }
        return models;
    }

    private static int getReIdByUrl(String url) {
        int reId = -1;
        switch (url) {
            case "NEW_TASK":    //新建工单
                reId = R.mipmap.new_repair;
                break;
            case "NEW_TEMPLATE":    //新建模板
                reId = R.mipmap.new_template;
                break;
            case "MY_TEMPLATE":     //我的模板
                reId = R.mipmap.my_template;
                break;
            case "CONSTRACT_SEARCH":    //合同信息
                reId = R.mipmap.service;
                break;
            case "UNFINISHED_TASK":     //未完成
                break;
            case "HANDLE_TASK":     //已处理
                break;
            case "UNHANDLE_TASK":      //待处理
                break;
            case "MAP_STYLE":   //地图模式
                break;
            case "FINISHED_TASK":   //已完成
                break;
            case "SR_PROCESS":   //服务报告处理
                break;
            case "CHANGE_STATUS":   //更改状态
                int statue = User.getUserFromRealm().getStatue();
                if (statue == 0) {
                    reId = R.mipmap.state_false;
                } else {
                    reId = R.mipmap.changestate;
                }
                break;
            case "KNOWLEDGE_SEARCH":    //知识检索,图片暂未给
            case "KNOWLEDGE_QUERY":
                reId = R.mipmap.service;
                break;
            case "SR_SEARCH"://服务报告
                reId = R.mipmap.service;
                break;
            case "ACCOUNT_BOOK_QUERY"://台账
                reId = R.mipmap.service;
                break;
          /*  case "REPORT_LOCATION":
                reId = R.mipmap.shangbaoweizhi;
                break;*/
        }
        return reId;
    }
}
