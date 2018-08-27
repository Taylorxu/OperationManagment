package com.wisesignsoft.OperationManagement.mview;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;
import com.wisesignsoft.OperationManagement.bean.DictDatasBean;
import com.wisesignsoft.OperationManagement.mview.widget.WheelView;
import com.wisesignsoft.OperationManagement.mview.widget.adapters.ArrayWheelAdapter;

import java.util.List;

/**
 * Created by ycs on 2016/8/12.
 */
public class BottomDialog extends Dialog implements View.OnClickListener {
    //清除
    private TextView title_cancel;
    //确定
    private TextView title_sure;
    //点击监听
    private OnTitleClickListener listener;
    private WheelView wv_address;
    private List<DictDatasBean> datas;
    private String province;
    private String name;

    public BottomDialog(Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private Context mContext;

    private void init(Context context) {
        this.mContext = context;
        setContentView(R.layout.address_dialog);
        //设置弹出窗口的位置
        setWindow();
        setUpViews();
        setUpListener();
    }

    //从底部弹出
    private void setWindow() {
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.BOTTOM;
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(params);
        //设置一个弹出动画
        window.setWindowAnimations(R.style.popwin_anim_style);
        window.getDecorView().setPadding(0, 0, 0, 0);
    }

    //放出点击事件监听
    public void setOnTitleClickListener(OnTitleClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_left:
                dismiss();
                break;
            case R.id.title_right:
                showSelectedResult();
                listener.onTitleRightClickListener(province,name);
                dismiss();
                break;
        }
    }

    public interface OnTitleClickListener {
        void onTitleRightClickListener(String province, String name);
    }

    private void setUpViews() {
        wv_address = (WheelView) findViewById(R.id.wv_address);
        title_cancel = (TextView) findViewById(R.id.title_left);
        title_sure = (TextView) findViewById(R.id.title_right);
    }

    private void setUpListener() {
        // 添加change事件,省
        title_cancel.setOnClickListener(this);
        title_sure.setOnClickListener(this);
    }

    public void setUpData(List<DictDatasBean> datas) {
        this.datas = datas;
        wv_address.setViewAdapter(new ArrayWheelAdapter(mContext, datas));
        // 设置可见条目数量
        wv_address.setVisibleItems(7);
    }

    private void showSelectedResult() {
        try {
            province = datas.get(wv_address.getCurrentItem()).getDictId();
            name = datas.get(wv_address.getCurrentItem()).getDictName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
