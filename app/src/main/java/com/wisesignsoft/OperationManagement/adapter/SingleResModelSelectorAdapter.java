package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;

import java.util.List;
import java.util.Map;

/**
 * Created by ycs on 2016/12/7.
 */

public class SingleResModelSelectorAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Map<String, String>> datas;
    private IContractInfo iContractInfo;

    public SingleResModelSelectorAdapter(Context context, List<Map<String, String>> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_time;
        TextView tv_company;

        public MyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_select_account_title);
            tv_time = (TextView) itemView.findViewById(R.id.tv_item_select_account_time);
            tv_company = (TextView) itemView.findViewById(R.id.tv_item_select_account_company);
        }
    }

    public interface IContractInfo {
        void setOnClick(int position);
    }

    public void setOnIContractInfo(IContractInfo iContractInfo) {
        this.iContractInfo = iContractInfo;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_single_select_res_model, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            Map<String, String> bean = datas.get(position);
            String key = "";
            String content = "", tv_title;

            if (!TextUtils.isEmpty(bean.get("PROJ_NAME")) || !TextUtils.isEmpty(bean.get("PROJ_NA"))) {
                tv_title = bean.get("PROJ_NAME") == null ? bean.get("PROJ_NA") : bean.get("PROJ_NAME");
            } else {
                tv_title = bean.get("RES_RENA") == null ? bean.get("K_TITLE") : bean.get("RES_RENA");
            }
            ((MyHolder) holder).tv_title.setText(tv_title);


            if (!TextUtils.isEmpty(bean.get("CUST_NAME"))) {
                content = bean.get("CUST_NAME");
            }
            if (!TextUtils.isEmpty(bean.get("CUST_NO"))) {
                content += "(" + bean.get("CUST_NO") + ")";
            }

            String time = bean.get("CREATEDATE");

            ((MyHolder) holder).tv_company.setText(content);
            ((MyHolder) holder).tv_time.setText(time);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iContractInfo.setOnClick(position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
