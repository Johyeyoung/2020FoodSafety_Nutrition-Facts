package com.newfact.newfacts.productDetail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.newfact.newfacts.R;
import com.newfact.newfacts.menu.Product;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productTitle;
        TextView productFranchise;
        public final View mView;

        MyViewHolder(View view){
            super(view);
            mView = itemView;

            productImage = view.findViewById(R.id.product_image);
            productTitle =view.findViewById(R.id.product_title);
            productFranchise = view.findViewById(R.id.product_franchise);

        }
    }

    private ArrayList<Product> foodInfoArrayList;
    public Myadapter(ArrayList<Product> foodInfoArrayList){
        this.foodInfoArrayList = foodInfoArrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Glide.with(myViewHolder.itemView.getContext())
                .load(foodInfoArrayList.get(position).getPic())
                .into(myViewHolder.productImage);
        myViewHolder.productFranchise.setText(foodInfoArrayList.get(position).getFranchise());
        myViewHolder.productTitle.setText(foodInfoArrayList.get(position).getName());


        // 인덴트 받아서 다음 페이지 넘어가기
        myViewHolder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ProductDetailPage.class);
                intent.putExtra("detail", foodInfoArrayList.get(position).toString());  // 검색어 전달 (스타벅스/나이트로 콜드 브루/임시)
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return foodInfoArrayList.size();
    }
}
