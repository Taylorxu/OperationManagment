package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.TempletListBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ycs on 2016/12/6.
 */

public class MyTemplateAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<TempletListBean.DataBean> datas;
    private IMyTemplate iMyTemplate;

    public MyTemplateAdapter(Context context, List<TempletListBean.DataBean> datas) {
        this.context = context;
        this.datas = datas;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_name;
        TextView tv_time;

        public MyHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_item_my_template_title);
            tv_name = (TextView) itemView.findViewById(R.id.tv_item_my_template_name);
            tv_time = (TextView) itemView.findViewById(R.id.tv_item_my_template_time);
        }
    }

    public interface IMyTemplate {
        void setOnClick(String id, String name);
    }

    public void setOnIMyTemplate(IMyTemplate iMyTemplate) {
        this.iMyTemplate = iMyTemplate;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_my_template, parent, false);
        return new MyTemplateAdapter.MyHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyTemplateAdapter.MyHolder) {
            final TempletListBean.DataBean bean = datas.get(position);
            String title = bean.getSketchTitle();
            String name = bean.getProcessKeyDes();
            if (!TextUtils.isEmpty(title)) {
                ((MyHolder) holder).tv_title.setText(bean.getSketchTitle());
            }
            if (!TextUtils.isEmpty(name)) {
                ((MyHolder) holder).tv_name.setText(bean.getProcessKeyDes());
            }
            TempletListBean.DataBean.SaveTimeBean bean1 = bean.getSaveTime();
            long num = bean1.getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
            Date dt = new Date(num);
            String sDateTime = sdf.format(dt);  //得到精确到秒的表示：08/31/2006 21:08:00
            ((MyHolder) holder).tv_time.setText(sDateTime);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    iMyTemplate.setOnClick(bean.getId(), bean.getProcessKey());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }
}
