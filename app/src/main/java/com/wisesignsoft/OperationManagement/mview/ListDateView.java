package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.utils.DisplayUtils;

/**
 * Created by ycs on 2016/12/17.
 */

public class ListDateView extends LinearLayout {

    private LinearLayout ll_date_list;
    private LinearLayout ll_list_date;
    private TextView tv_list_date;
    private TextView tv_list_date_title;
    private ImageView iv_list_date_view;
    private IListDateClickListener listDateClickListener;
    private boolean ismodify;

    public ListDateView(Context context) {
        super(context);
        init(context);
    }

    private void init(final Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_date_view, this, true);
        tv_list_date_title = (TextView) view.findViewById(R.id.tv_list_date_title);
        iv_list_date_view = (ImageView) view.findViewById(R.id.iv_list_date_view);
        ll_list_date = (LinearLayout) view.findViewById(R.id.ll_list_date);
        ll_date_list = (LinearLayout) view.findViewById(R.id.ll_date_list);
        tv_list_date = (TextView) view.findViewById(R.id.tv_list_date);
        ll_list_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listDateClickListener != null) {
                    listDateClickListener.setOnAddView();
                }
            }
        });
        iv_list_date_view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_list_date_view.setSelected(!iv_list_date_view.isSelected());
                if (iv_list_date_view.isSelected()) {
                    ll_date_list.setVisibility(VISIBLE);
                    ll_list_date.setVisibility(ismodify ? VISIBLE : GONE);
                } else {
                    ll_date_list.setVisibility(GONE);
                    ll_list_date.setVisibility(GONE);
                }

            }
        });
        iv_list_date_view.setSelected(false);
        ll_date_list.setVisibility(GONE);
        ll_list_date.setVisibility(GONE);
    }

    /**
     * 这个是父类的方法，给不同子类设置不同的属性的
     *
     * @param context
     * @param reId
     * @param text
     */
    public void initView(Context context, int reId, String text) {
        tv_list_date.setText(text);
        Drawable male = getResources().getDrawable(reId);
        male.setBounds(0, 0, DisplayUtils.sp2px(context, 15), DisplayUtils.sp2px(context, 15));
        tv_list_date.setCompoundDrawables(male, null, null, null);//男
    }

    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tv_list_date_title.setText(title);
        }
    }

    /**
     * 添加表格view
     *
     * @param standBookView
     */
    public void addStandBook(final StandBookView standBookView) {
        ll_date_list.addView(standBookView);
        standBookView.setOnIStandBookClickListener(new StandBookView.IStandBookClickListener() {
            @Override
            public void setDel() {
                removeView(standBookView);
                listDateClickListener.setOnDelView(standBookView);
            }
        });
    }

    private void removeView(StandBookView standBookView) {
        ll_date_list.removeView(standBookView);
        //删除时 修改 每个 standbookview 的游标值
        for (int i = 0; i < ll_date_list.getChildCount(); i++) {
            StandBookView child = (StandBookView) ll_date_list.getChildAt(i);
            child.setDeletePosition(i);
        }
    }

    public void setIListDataClickListener(IListDateClickListener listDataClickListener) {
        this.listDateClickListener = listDataClickListener;
    }

    public interface IListDateClickListener {
        void setOnAddView();

        void setOnDelView(StandBookView standBookView);
    }

    public void clearView() {
        ll_date_list.removeAllViews();
    }

    public void hideAddBtn(boolean modify) {
        ismodify = modify;
    }

    /**
     * 当在 选择台账 界面选择一个台账返回时，需要显示控件
     */
    public void setGridListShowOrHide(Boolean cases) {
        //已经显示，怎不显示
        if (iv_list_date_view.isSelected()) return;

        iv_list_date_view.setSelected(cases);
        if (iv_list_date_view.isSelected()) {
            ll_date_list.setVisibility(VISIBLE);
            ll_list_date.setVisibility(ismodify ? VISIBLE : GONE);
        } else {
            ll_date_list.setVisibility(GONE);
            ll_list_date.setVisibility(GONE);
        }
    }
}
