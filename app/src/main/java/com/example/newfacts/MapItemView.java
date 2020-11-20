package com.newfact.newfacts;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class MapItemView extends LinearLayout {
    TextView text1, text3;
    ImageView img;

    public MapItemView(Context context) {
        super(context);
        init(context);
    }

    public MapItemView(Context context, @Nullable AttributeSet attrs) {
        super(context);
    }

    public void init(Context context){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.map_item_list, this, true);

        text1 = findViewById(R.id.text1);
        text3 = findViewById(R.id.text3);
        img = findViewById(R.id.img_color);
    }

    public void setCafeName(String cafe_name){
        text1.setText(cafe_name);
    }
    public void setDistance(String distance){
        text3.setText(distance);
    }
    public void setImg(int resId){img.setImageResource(resId);}
}
