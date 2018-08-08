package com.wisesignsoft.OperationManagement.mview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wisesignsoft.OperationManagement.R;


public class SelectorView extends RelativeLayout {

    private ImageView imageView;
    private TextView textView;
    private boolean isSelect;

    public SelectorView(Context context) {
        super(context);
        init(context);
    }

    public SelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.selector_view, this, true);
        imageView = (ImageView) view.findViewById(R.id.iv_selector_view);
        textView = (TextView) view.findViewById(R.id.tv_selector_view);
    }

    public void setInit(int reId, String text) {
        if (reId != -1) {
            imageView.setImageResource(reId);
        }
        if (!TextUtils.isEmpty(text)) {
            textView.setText(text);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
    }

    public void setSelect(boolean isSelect) {
        this.isSelect = isSelect;
        if (isSelect) {
//            if (!textView.isSelected())
                textView.setSelected(true);
//            if (!imageView.isSelected())
                imageView.setSelected(true);
        } else {
//            if (textView.isSelected())
                textView.setSelected(false);
//            if (imageView.isSelected())
                imageView.setSelected(false);
        }
    }

    public boolean isSelect() {
        return isSelect;
    }
}
