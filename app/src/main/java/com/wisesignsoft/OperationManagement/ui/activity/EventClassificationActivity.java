package com.wisesignsoft.OperationManagement.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.wisesignsoft.OperationManagement.BaseActivity;
import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.EventClassificationModel;
import com.wisesignsoft.OperationManagement.db.WorkOrderDataManager;
import com.wisesignsoft.OperationManagement.mview.MyTitle;
import com.wisesignsoft.OperationManagement.mview.NextView;
import com.wisesignsoft.OperationManagement.utils.TempTreeSelectionDataManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EventClassificationActivity extends BaseActivity {

    private static boolean isMult;
    private MyTitle mt_event;
    private RecyclerView rv_event;
    private String id;
    private static List<String> checkedList = new ArrayList<>();

    public static void startSelf(Context context, List<EventClassificationModel> datas, String id) {
        Intent intent = new Intent(context, EventClassificationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", (Serializable) datas);
        intent.putExtras(bundle);
        intent.putExtra("id", id);
        intent.putExtra("isMult", isMult);
        context.startActivity(intent);
    }

    public static void startSelf(Context context, List<EventClassificationModel> datas, String id, boolean isMult) {
        Intent intent = new Intent(context, EventClassificationActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("datas", (Serializable) datas);
        intent.putExtras(bundle);
        intent.putExtra("id", id);
        intent.putExtra("isMult", isMult);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_classification);
        TempTreeSelectionDataManager.getManager().addEventClassificationActivity(this);
        init();
        request();
    }

    private void init() {
        id = getIntent().getStringExtra("id");
        isMult = getIntent().getBooleanExtra("isMult", false);

        mt_event = (MyTitle) findViewById(R.id.mt_event_classification);
        rv_event = (RecyclerView) findViewById(R.id.rv_event_classification);
        mt_event.setBack(true, this);
        mt_event.setTitle(getResources().getString(R.string.event_classification));
        mt_event.setTvRight(isMult, getString(R.string.sure), new EnsureChecked());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        rv_event.setLayoutManager(manager);
    }


    private void request() {
        List<EventClassificationModel> datas = (List<EventClassificationModel>) getIntent().getSerializableExtra("datas");
        for (EventClassificationModel model : datas) {
            arrageCheckList(model);
            if (model.getList() != null)
                for (EventClassificationModel model1 : model.getList()) {
                    arrageCheckList(model1);
                }

        }
        initData(datas);
    }

    private void arrageCheckList(EventClassificationModel model) {
        String id = model.getDictId();
        if (model.isSelected()) {
            if (checkedList.size() > 0) {
                if (!checkedList.contains(id)) {
                    checkedList.add(id);
                }
            } else {
                checkedList.add(id);
            }
        } else {
            if (checkedList.size() > 0) {
                for (int o = 0; o < checkedList.size(); o++) {
                    if (checkedList.get(o).equals(id)) checkedList.remove(id);
                }
            }
        }
    }

    private void initData(List<EventClassificationModel> datas) {
        MyAdapter adapter = new MyAdapter(this, datas);
        rv_event.setAdapter(adapter);
    }

    private class EnsureChecked implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (checkedList.size() > 0) {
                String result = checkedList.toString();
                result = result.substring(1, result.length() - 1).replace(" ", "");
                TempTreeSelectionDataManager.getManager().setTemp(result);
                TempTreeSelectionDataManager.getManager().clearEventClassificationActivity();
                WorkOrderDataManager.newInstance().modifyValue(id, result);
            }
        }
    }


    private class MyAdapter extends RecyclerView.Adapter {

        private Context context;
        private List<EventClassificationModel> datas;

        public MyAdapter(Context context, List<EventClassificationModel> datas) {
            this.context = context;
            this.datas = datas;
        }

        private class MyHolder extends RecyclerView.ViewHolder {
            NextView tv;
            CheckBox checkBox;

            public MyHolder(View itemView) {
                super(itemView);
                tv = (NextView) itemView.findViewById(R.id.nv_item_event_classification);
                checkBox = (CheckBox) itemView.findViewById(R.id.cb_event);
            }
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_event_classification, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyHolder) {
                final EventClassificationModel model = datas.get(position);
                ((MyHolder) holder).tv.setData(model.getDictName());
                if (isMult) {//多选
                    ((MyHolder) holder).checkBox.setVisibility(View.VISIBLE);
                    ((MyHolder) holder).checkBox.setChecked(model.isSelected());
                    ((MyHolder) holder).checkBox.setTag(model.getDictId());
                    ((MyHolder) holder).checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            if (isChecked) {
                                checkedList.add(buttonView.getTag().toString());
                            } else {
                                checkedList.remove(buttonView.getTag().toString());
                            }
                        }
                    });
                }
                if (model.getList() == null || model.getList().size() == 0) {
                    ((MyHolder) holder).tv.setIV(false);
                    if (!isMult) {//no多选
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String temp = model.getDictId();
                                WorkOrderDataManager.newInstance().modifyValue(id, temp);
                                TempTreeSelectionDataManager.getManager().setTemp(temp);
                                TempTreeSelectionDataManager.getManager().clearEventClassificationActivity();
                            }
                        });
                    }
                } else {
                    ((MyHolder) holder).tv.setIV(true);
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            datas = model.getList();
                            EventClassificationActivity.startSelf(EventClassificationActivity.this, datas, id);
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


    @Override
    protected void onDestroy() {
        super.onDestroy();
        TempTreeSelectionDataManager.getManager().removeEventClassificationActivity(this);
    }
}
