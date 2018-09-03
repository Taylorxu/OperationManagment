package com.wisesignsoft.OperationManagement.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;

import java.util.List;

/**
 * Created by ycs on 2016/12/5.
 */

public class CheckBoxViewAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<DictDatasBean> list;
    private List<String> ids;
    private ICheckBoxViewListener listener;
    private boolean isClick = false;

    public CheckBoxViewAdapter(Context context, List<DictDatasBean> list) {
        this.context = context;
        this.list = list;
    }

    public interface ICheckBoxViewListener {
        void setOnItemClickListenenr();
    }

    public void setOnICheckBoxListener(ICheckBoxViewListener listener) {
        this.listener = listener;
    }

    private static class MyHolder extends RecyclerView.ViewHolder {
        TextView tv;
        ImageView iv;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_new_work_order);
            iv = (ImageView) itemView.findViewById(R.id.iv_tuceng);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_work_order, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MyHolder) {
            final DictDatasBean str = list.get(position);
            ((MyHolder) holder).tv.setText(str.getDictName());
            setImgSelect(((MyHolder) holder).iv, false,((MyHolder) holder).tv);
            if(ids != null && ids.size()>0){
                for (String id : ids) {
                    if (id.equals(str.getDictId())) {
                        setImgSelect(((MyHolder) holder).iv, true,((MyHolder) holder).tv);
                    }
                }
            }
            ((MyHolder) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(isClick){
                        if (((MyHolder) holder).tv.isSelected()) {
                            ids.remove(str.getDictId());
                            setImgSelect(((MyHolder) holder).iv, false,((MyHolder) holder).tv);
                        } else {
                            ids.add(str.getDictId());
                            setImgSelect(((MyHolder) holder).iv, true,((MyHolder) holder).tv);
                        }
                        listener.setOnItemClickListenenr();
                    }
                }
            });
        }
    }

    private void setImgSelect(ImageView iv, boolean isSelect, TextView textView) {
        if (isSelect) {
            textView.setSelected(true);
            iv.setVisibility(View.VISIBLE);
        } else {
            textView.setSelected(false);
            iv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return (list == null || list.size() == 0) ? 0 : list.size();
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public List<String> getIds() {
        return ids;
    }
    public void setClick(boolean isClick){
        this.isClick = isClick;
    }
}
