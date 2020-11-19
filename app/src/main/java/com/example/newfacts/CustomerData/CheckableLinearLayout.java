package com.example.newfacts.CustomerData;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.example.newfacts.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox)findViewById(R.id.checkBox1);
        if(cb.isChecked()!=checked){
            cb.setChecked(checked);
        }
    }

    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox)findViewById(R.id.checkBox1);
        return cb.isChecked();
    }

    @Override
    public void toggle() {//현재 checked 상태를 바꾸면 UI에 반영
        CheckBox cb = (CheckBox) findViewById(R.id.checkBox1) ;
        setChecked(cb.isChecked() ? false : true) ;

    }
}
