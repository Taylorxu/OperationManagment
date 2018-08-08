package com.wisesignsoft.OperationManagement.mview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.wisesignsoft.OperationManagement.R;

/**
 * Created by ycs on 2016/6/15.
 */
@SuppressLint("AppCompatCustomView")
public class MySwitch extends ImageView {
    public MySwitch(Context context) {
        super(context);
        initView(context);
    }

    public MySwitch(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MySwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
    private void initView(Context context){
        setBackgroundResource(R.drawable.switch_selector);
    }

    public void setData(boolean initStatue){
        setSelected(initStatue);
    }

    /**
     * 暴露点击事件
     * @param listener
     */
    public void setSwitchListener(final SwitchClickListener listener){
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelected()){
                    setSelected(false);
                }else {
                    setSelected(true);
                }
                listener.setSwitchClickListener();
            }
        });
    }
    public interface SwitchClickListener{
        void setSwitchClickListener();
    }
}
