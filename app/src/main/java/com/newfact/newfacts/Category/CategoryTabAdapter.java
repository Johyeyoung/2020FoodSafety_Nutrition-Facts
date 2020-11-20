package com.newfact.newfacts.Category;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.newfact.newfacts.Category.CategoryDefaultRecycler;

import java.util.ArrayList;

public class CategoryTabAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> arrayList = new ArrayList<>();
        private ArrayList<String> name = new ArrayList<>();

    public CategoryTabAdapter(@NonNull FragmentManager fm)
        {
            super(fm);

            // 탭에 주입한 프래그먼트 생성

            CategoryDefaultRecycler f1 = new CategoryDefaultRecycler();
            CategoryDefaultRecycler f2 = new CategoryDefaultRecycler();
            CategoryDefaultRecycler f3 = new CategoryDefaultRecycler();
            CategoryDefaultRecycler f4 = new CategoryDefaultRecycler();
            CategoryDefaultRecycler f5 = new CategoryDefaultRecycler();

            Bundle bundle0 = new Bundle();
            bundle0.putInt("Tab",0);
            f1.setArguments(bundle0);

            Bundle bundle1 = new Bundle();
            bundle1.putInt("Tab",1);
            f2.setArguments(bundle1);

            Bundle bundle2 = new Bundle();
            bundle2.putInt("Tab",2);
            f3.setArguments(bundle2);

            Bundle bundle3 = new Bundle();
            bundle3.putInt("Tab",3);
            f4.setArguments(bundle3);

            Bundle bundle4 = new Bundle();
            bundle4.putInt("Tab",4);
            f5.setArguments(bundle4);

            arrayList.add(f1);
            arrayList.add(f2);
            arrayList.add(f3);
            arrayList.add(f4);
            arrayList.add(f5);

            //탭의 이름 지정

            name.add("카페/라떼");
            name.add("스무디");
            name.add("에이드");
            name.add("티");
            name.add("기타");
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return name.get(position);
        }

        @NonNull
        @Override
        public Fragment getItem(int position)
        {
            return arrayList.get(position);
        }

        @Override
        public int getCount()
        {
            return arrayList.size();
        }
}
