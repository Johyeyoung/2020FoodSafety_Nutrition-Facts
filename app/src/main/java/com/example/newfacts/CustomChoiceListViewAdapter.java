package com.example.newfacts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CustomChoiceListViewAdapter extends BaseAdapter {

    private ArrayList<com.example.newfacts.menu.ListViewItem> listViewItemList = new ArrayList<com.example.newfacts.menu.ListViewItem>();

    public CustomChoiceListViewAdapter(){}

    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // listview_item.xml layout을 inflate하여 convertView를 참조 획득함
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView textView1 = (TextView)convertView.findViewById(R.id.textView1);

        com.example.newfacts.menu.ListViewItem listViewItem = listViewItemList.get(position);

        textView1.setText(listViewItem.getText());
        return convertView;
    }


    public void addItem(String text){
        com.example.newfacts.menu.ListViewItem item = new com.example.newfacts.menu.ListViewItem();

        item.setText(text);
        listViewItemList.add(item);
    }
}
