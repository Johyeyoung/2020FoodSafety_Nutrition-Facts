package com.newfact.newfacts.Category;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.newfact.newfacts.R;
import com.google.android.material.tabs.TabLayout;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

public class CategoryTabMain extends Fragment {

    ViewPager viewPager;
    int message;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_tab, container, false);

        Bundle bundle = getArguments();
        if(bundle != null){

            // 음료의 카테고리 중  CategoryGridMenuAdapter의 리스너에서 보낸 태그를 받는다.

            message = bundle.getInt("Key");

            //Toast.makeText(view.getContext(), "Integer.toString(message)", Toast.LENGTH_SHORT).show();
        }

        // 사용자가 음료의 카테고리를 편하게 스와이프(넘겨) 볼 수 있도록 viewpager 사용

        viewPager = view.findViewById(R.id.viewpager);

        try {

            // 프래그먼트 위에서 프래그먼트(Nested Fragment)로 getChildFragmentManger()를 호출

            CategoryTabAdapter adapter = new CategoryTabAdapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);

        }catch (Exception e){

            Log.e(TAG, "...",e); // getChildFragmentManger()

        }

        TabLayout tabLayout = view.findViewById(R.id.layout_tab);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(message).select(); // 커피/라떼를 선택하면 해당 하는 탭으로 고정

        return view;
    }
}
