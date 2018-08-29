package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.wisesignsoft.OperationManagement.R;

/**
 * Created by ycs on 2017/1/4.
 */

public class NoteButtonView extends FrameLayout {

    private Button bt;

    public NoteButtonView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.note_button, this, true);
        bt = (Button) view.findViewById(R.id.bt);
    }

    public void setData(String title) {
        if(!TextUtils.isEmpty(title)){
            bt.setText(title);
        }
    }

    public void setBtOnClickListener(OnClickListener listener){
        if(listener != null){
            bt.setOnClickListener(listener);
        }
    }
}
