package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.Children;
import com.wisesignsoft.OperationManagement.mview.NextView;
import com.wisesignsoft.OperationManagement.ui.activity.AllDeptTreeActivity;
import com.wisesignsoft.OperationManagement.utils.TempTreeSelectionDataManager;

import java.util.List;

/**
 * Created by ycs on 2016/12/15.
 */

public class AllDeptTreeAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Children> datas;
    private String id;
    private boolean hasMult;

    public AllDeptTreeAdapter(Context context, List<Children> datas, String id, boolean hasMult) {
        this.context = context;
        this.datas = datas;
        this.hasMult = hasMult;
        this.id = id;
    }

    private class MyHolder extends RecyclerView.ViewHolder {
        NextView tv;
        ImageView checkBox_view;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (NextView) itemView.findViewById(R.id.nv_item_all_dept_tree);
            checkBox_view = (ImageView) itemView.findViewById(R.id.cb_depart);
        }
    }

    public interface IAllDeptTreeListener {
        void setOnIAllDeptTreeClick(String id);
    }

    public interface MultableCheckListener {
        void setMultableCheckClick(Children model);
    }

    private IAllDeptTreeListener listener;
    private MultableCheckListener mulCheckListener;

    public void setOnIClick(IAllDeptTreeListener listener) {
        this.listener = listener;
    }

    public void setMultableCheckClick(MultableCheckListener listener) {
        this.mulCheckListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_dapt_tree, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            final Children model = datas.get(position);
            ((MyHolder) holder).tv.setData(model.getDeptFullName());
            if (hasMult) {// 多选
                if (!model.getDeptId().equals("root"))
                    ((MyHolder) holder).checkBox_view.setVisibility(hasMult ? View.VISIBLE : View.GONE);//多选时的复选框
                ((MyHolder) holder).checkBox_view.setSelected(model.isSelected());
                ((MyHolder) holder).checkBox_view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        model.setSelected(!v.isSelected());
                        ((MyHolder) holder).checkBox_view.setSelected(!v.isSelected());
                        mulCheckListener.setMultableCheckClick(model);
                    }
                });
            }
            if (model.getChildren() == null || model.getChildren().size() == 0) {//最后一层
                ((MyHolder) holder).tv.setIV(false);
                if (!hasMult) {//单选直接finish（原来的单击事件）
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String temp = model.getDeptFullName();
                            TempTreeSelectionDataManager.getManager().setTemp(temp);
                            TempTreeSelectionDataManager.getManager().clearAllDeptTreeActivity();
                            listener.setOnIAllDeptTreeClick(model.getDeptId());
                        }
                    });
                }

            } else {
                ((MyHolder) holder).tv.setIV(true);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        datas = model.getChildren();
                        AllDeptTreeActivity.startSelf(context, datas, id);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return (datas == null || datas.size() == 0) ? 0 : datas.size();
    }


}
