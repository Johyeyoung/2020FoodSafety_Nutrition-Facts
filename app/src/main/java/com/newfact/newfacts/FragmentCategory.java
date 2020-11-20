package com.newfact.newfacts;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newfact.newfacts.Category.CategoryGridMenuAdapter;
import com.newfact.newfacts.Category.CategoryGridMenuItem;
import com.newfact.newfacts.Category.CategoryThemeAdapter;
import com.newfact.newfacts.Category.CategoryThemeItem;
import com.newfact.newfacts.Category.CategoryThemeAdapter;
import com.newfact.newfacts.R;

import java.util.ArrayList;

public class FragmentCategory extends Fragment {

    ArrayList<CategoryThemeItem> categoryList;
    RecyclerView recyclerView;
    CategoryThemeAdapter adapter;
    LinearLayoutManager layoutManager;

    ArrayList<CategoryGridMenuItem> catList;
    RecyclerView catGridRecyclerView;
    CategoryGridMenuAdapter gridAdapter;
    GridLayoutManager gridLayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // 서진영
        // 가. 카테고리 화면 상단에 표시할 테마 : 메뉴를 카테고리 별로 분류할 뿐만 아니라 '추천메뉴' 등의 테마로 음료를 즐길 수 있게 함.

        categoryList = new ArrayList<>();

        // 1. 서버를 사용하지 않고 임시로 데이터 지정
        categoryList.add(new CategoryThemeItem(R.drawable.theme1,"추천메뉴 : 바닐라라떼","당신이 좋아하는 맛"));
        categoryList.add(new CategoryThemeItem(R.drawable.theme2,"랜덤메뉴","뭐 먹을지 고민이라면"));
        categoryList.add(new CategoryThemeItem(R.drawable.theme3,"0 칼로리 메뉴","맛있게 먹어도 0KCAL"));

        // 2.리사이클러뷰에 LinearLayoutManager 객체 지정.

        recyclerView = view.findViewById(R.id.categoryRecycler) ;
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new CategoryThemeAdapter(categoryList) ;
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // 나. 음료 카테고리를 위한 데이터셋 준비

        catList = new ArrayList<>();
        catList.add(new CategoryGridMenuItem("커피/라떼", R.drawable.espresso));
        catList.add(new CategoryGridMenuItem("스무디",R.drawable.smoothe));
        catList.add(new CategoryGridMenuItem("에이드",R.drawable.ade));
        catList.add(new CategoryGridMenuItem("차",R.drawable.tea));
        catList.add(new CategoryGridMenuItem("모두보기",R.drawable.all));

        catGridRecyclerView = view.findViewById(R.id.catGridRecycler) ;
        gridLayoutManager = new GridLayoutManager(getActivity(), 3); // 그리드 레이아웃 사용

        catGridRecyclerView.setLayoutManager(gridLayoutManager);
        catGridRecyclerView.setHasFixedSize(true);

        gridAdapter = new CategoryGridMenuAdapter(catList);

        catGridRecyclerView.setAdapter(gridAdapter);
        gridAdapter.notifyDataSetChanged();

        return view;
    }
}