package com.newfact.newfacts.Category;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newfact.newfacts.productDetail.Myadapter;
import com.newfact.newfacts.R;
import com.newfact.newfacts.menu.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.newfact.newfacts.menu.Product;

import java.util.ArrayList;

public class CategoryDefaultRecycler extends Fragment {

    int message;
    String query;
    RecyclerView mRecyclerView;
    LinearLayoutManager mLayoutManager;
    Myadapter adapter;
    ArrayList<Product> data;


    //카테고리에서 많이 사용되는 음료 디스플레이용 프래그먼트

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.category_recycler, container, false);

        Bundle bundle = getArguments();

        if(bundle != null){

            // message = bundle.getInt("Tab");
            // Toast.makeText(view.getContext(), Integer.toString(message), Toast.LENGTH_SHORT).show();

            message = bundle.getInt("Tab");

            // 0 ~ 4 : 탭레이아웃으로 메뉴 카테고리를 뷰페이저로 넘겨 볼 수 있는데 사용되는 프래그먼트 + 데이터 호출
            // 그 외 : 카드뷰로 제공되는 각종 테마에 해당하는 프래그먼트 + 음료 호츌

            switch (message){

                case 0:
                    query = "커피/라떼"; // 파이어베이스 사용 시 필요
                    break;
                case 1:
                    query = "스무디";
                    break;
                case 2:
                    query = "에이드";
                    break;
                case 3:
                    query = "티";
                    break;
                case 4:
                    query = "기타";
                    break;
                case 5:
                    break;
                case 6:
                    query = "랜덤메뉴";
                    break;
                case 7:
                    query = "0";
                    break;

            }

            // Toast.makeText(view.getContext(), Integer.toString(message) + query, Toast.LENGTH_SHORT).show();
        }

        // 2. 리사이클러뷰 처리

        mRecyclerView = (RecyclerView) view.findViewById(R.id.CategoryDefaultRecycler); //
        mLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // 데이터 저장 준비
        data = new ArrayList<>();


        // * Firebase : 조혜영 학우 구현 (주석 : 서진영)

        // 싱글톤 패턴으로 구현 : 여러 프래그먼트가 동시에 동작할 경우 오류.

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference().child("data");
        ValueEventListener mValueEventListener = new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {

                    // 파이어베이스에서 데이터를 불러옴.

                    Product product = datasnapshot.getValue(Product.class); // 데이터를 불러와서
                    product.setKey(datasnapshot.getKey()); // 데이터의 키(고유값) 값을 product의 키로 지정


                    // 조건문에 의해 필터링된 데이터가 저장됨
                    String search;

                    if(message < 5){

                        // 탭에서 사용할 데이터 : 카테고리별 호출

                        search = product.getCategory().replaceAll(" ", "");

                        if(search.contains(query.replaceAll(" ", ""))){

                            data.add(product);
                        }

                    }else if(message == 5){

                        // 추천메뉴 임시 데이터로 구현 : 텐서플로우로 ML,DL을 활용하고자 하였으나 데이터가 없다.

                        search = product.getName().replaceAll(" ", "");
                        query = "바닐라 라떼"; // 추천메뉴 지정

                        if(search.contains(query.replaceAll(" ", ""))){

                            data.add(product);
                        }
                    }else if(message == 6){

                        data.add(product);

                    }else{

                        // 파이어베이스에 칼로리/지방/단백질/나트륨/설탕/카페인으로 구성되어있다.
                        // "144/10/55/5/6/7/30
                        String[] nutrition = product.getNutrition().split("/");

                        if(nutrition[0].equals("0")){

                            // 칼로리가 0인 경우 저장

                            data.add(product);

                        }
                    }
                }

                adapter = new Myadapter(data);
                mRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }

        };
        dbRef.addValueEventListener(mValueEventListener);

        return view;
    }
}
