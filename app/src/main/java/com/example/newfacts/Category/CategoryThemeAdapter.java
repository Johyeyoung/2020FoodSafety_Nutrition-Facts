package com.newfact.newfacts.Category;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newfact.newfacts.MainActivity;
import com.newfact.newfacts.R;

import java.util.ArrayList;

public class CategoryThemeAdapter extends RecyclerView.Adapter<CategoryThemeAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView categoryImage;
        TextView categoryTitle;
        TextView categoryDesc;

        ViewHolder(View itemView) {
            super(itemView) ;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // 선택된 테마에 따라서, 해당하는 음료를 표시하기 위한 클릭리스너

                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {

                        Bundle args = new Bundle();

                        // 분기문에 따라서 CategoryDefaultRecycler에서 보여줄 데이터를 다르게 한다.

                        switch (pos){

                            case 0:
                                args.putInt("Tab",5); // 추천메뉴
                                break;
                            case 1:
                                args.putInt("Tab",6);
                                break;
                            case 2:
                                args.putInt("Tab",7);
                                break;

                        }

                        Fragment myFragment = new CategoryDefaultRecycler();
                        myFragment.setArguments(args);

                        FragmentManager fragmentManager = ((MainActivity)v.getContext()).getSupportFragmentManager();

                        // 다른 네비게이션 바로 이동하여도 상태를 유지할 수 있도록 지정.
                        // 그러나 MainActivity에서 commitAllowingStateLoss로 지정하여 상태 유지 불가 : 생명주기 관리 차원에서 타협.
                        fragmentManager.beginTransaction().replace(R.id.frameLayout, myFragment).addToBackStack(null).commit();

                    }
                }
            });

            // 뷰 객체에 대한 참조.
            categoryImage = itemView.findViewById(R.id.categoryImage);
            categoryTitle = itemView.findViewById(R.id.categoryTitle);
            categoryDesc = itemView.findViewById(R.id.categoryDesc);
        }
    }

    private ArrayList<CategoryThemeItem> categoryModels;

    public CategoryThemeAdapter(ArrayList<CategoryThemeItem> list) {

        this.categoryModels = list ;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // 부모 프래그먼트 위에 레이아웃으로 구현한 카드뷰 테마 주입

        Context context = parent.getContext() ;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) ;

        View view = inflater.inflate(R.layout.category_theme_item, parent, false) ;
        ViewHolder vh = new ViewHolder(view) ;

        return vh ;
    }

    // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.categoryImage.setImageResource(categoryModels.get(position).getImageResoureceID());
        holder.categoryTitle.setText(categoryModels.get(position).getTitle());
        holder.categoryDesc.setText(categoryModels.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }
}
