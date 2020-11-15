package com.example.newfacts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newfacts.menu.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends BaseAdapter {
    private List<Product> mData;
    private Map<String,Integer> mProductImageMap;

    public ProductAdapter(List<Product> data){
        this.mData=data;
        mProductImageMap = new HashMap<>();
//        mProductImageMap.put("selecto", R.drawable.bback);
//        mProductImageMap.put("coffeebean", R.drawable.coffeebean);
//        mProductImageMap.put("gongcha",R.drawable.gongcha);
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override

    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);

            ImageView productImage = convertView.findViewById(R.id.product_image);
            TextView productTitle= convertView.findViewById(R.id.product_title);
            TextView productFranchise= convertView.findViewById(R.id.product_franchise);
            holder.productImage=productImage;
            holder.productTitle=productTitle;
            holder.productFranchise= productFranchise;
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        Product product = mData.get(position);
        holder.productTitle.setText(product.getName());
        holder.productFranchise.setText(product.getFranchise());
//        holder.productImage.setImageResource(mProductImageMap.get(product.getCategory()));

        return convertView;
    }

    // ..........Holder 정의
    static class ViewHolder{
        ImageView productImage;
        TextView productTitle;
        TextView productFranchise;
    }
}