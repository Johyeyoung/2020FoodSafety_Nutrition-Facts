package com.newfact.newfacts.Category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newfact.newfacts.MainActivity;
import com.newfact.newfacts.R;

import java.util.ArrayList;

public class CategoryGridMenuAdapter extends RecyclerView.Adapter<CategoryGridMenuAdapter.ViewHolder> {

    ArrayList<CategoryGridMenuItem> categoryDetailModels;

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryDetailImage;
        TextView categoryDetailTitle;

        ViewHolder(View itemView) {
            super(itemView) ;

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {

                        Bundle args = new Bundle();

                        switch (pos){

                            case 0:
                                args.putInt("Key",0);
                                break;
                            case 1:
                                args.putInt("Key",1);
                                break;
                            case 2:
                                args.putInt("Key",2);
                                break;
                            case 3:
                                args.putInt("Key",3);
                                break;
                            case 4:
                                args.putInt("Key",4);
                                break;

                        }

                        //Toast.makeText(v.getContext(), categoryDetailModels.get(pos).getTitle(), Toast.LENGTH_SHORT).show();

                        Fragment myFragment = new CategoryTabMain();
                        myFragment.setArguments(args);

                        FragmentManager fragmentManager = ((MainActivity)v.getContext()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frameLayout, myFragment).addToBackStack(null).commit();

                    }
                }
            });

            // 뷰 객체에 대한 참조. (hold strong reference)
            categoryDetailImage = itemView.findViewById(R.id.categoryDetailImageview);
            categoryDetailTitle = itemView.findViewById(R.id.categoryDetailTitle);
        }
    }

    public CategoryGridMenuAdapter(ArrayList<CategoryGridMenuItem> list) {

        this.categoryDetailModels = list ;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 부모 프래그먼트 위에 그리드레이아웃으로 구현한 자식프래그먼트 카테고리 주입

        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.category_grid, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.categoryDetailImage.setImageResource(categoryDetailModels.get(position).getVectorId());
        holder.categoryDetailTitle.setText(categoryDetailModels.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return categoryDetailModels.size();
    }
}