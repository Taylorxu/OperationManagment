package com.wisesignsoft.OperationManagement.mview;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;

/**
 * Created by ycs on 2016/12/5.
 */

public class MyDialog extends Dialog implements View.OnClickListener {

    private TextView tv_title;
    private TextView tv_cancel;
    private TextView tv_sure;
    private MyDialog dialog;
    private IMyDialog iMyDialog;
    private IMyDialog2 iMyDialog2;

    public MyDialog(Context context) {
        super(context, R.style.my_dialog);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_dialog,null);
        tv_title = (TextView) view.findViewById(R.id.tv_my_dialog_title);
        tv_cancel = (TextView) view.findViewById(R.id.tv_my_dialog_cancel);
        tv_sure = (TextView) view.findViewById(R.id.tv_my_dialog_sure);
        setContentView(view);

    }
    /**
     * 对取消键没有特殊处理
     * @param title
     * @param dialog
     * @param iMyDialog
     */
    public void setData(String title, MyDialog dialog, IMyDialog iMyDialog){
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        if(dialog != null){
            this.dialog = dialog;
            this.iMyDialog = iMyDialog;
            tv_cancel.setOnClickListener(this);
            tv_sure.setOnClickListener(this);
        }
    }

    public void setData(String title, MyDialog dialog, IMyDialog2 iMyDialog2){
        if(!TextUtils.isEmpty(title)){
            tv_title.setText(title);
        }
        if(dialog != null){
            this.dialog = dialog;
            this.iMyDialog2 = iMyDialog2;
            tv_cancel.setOnClickListener(this);
            tv_sure.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_my_dialog_cancel:
                if(iMyDialog2 != null){
                    iMyDialog2.setMyDialogCancel();
                }
                dialog.cancel();
                dialog.dismiss();
                break;
            case R.id.tv_my_dialog_sure:
                if(iMyDialog2 != null){
                    iMyDialog2.setMyDialogSure();
                }
                if(iMyDialog != null){
                    iMyDialog.setMyDialog();
                }
                dialog.cancel();
                dialog.dismiss();
                break;
        }
    }
    public interface IMyDialog{
        void setMyDialog();
    }

    public interface IMyDialog2{
        void setMyDialogSure();
        void setMyDialogCancel();
    }
}
