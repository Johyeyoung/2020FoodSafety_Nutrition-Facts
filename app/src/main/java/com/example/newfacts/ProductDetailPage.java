package com.example.newfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.newfacts.menu.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductDetailPage extends AppCompatActivity {


    String franchise;
    String name;
    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_page);

        // 0. 전달된 검색어 받기 (스타벅스/나이트로 콜드 브루/임시)

        Intent intent = getIntent();
        String product = intent.getExtras().getString("detail");
        String[] productInfo = product.split("/");
        franchise = productInfo[0];
        name = productInfo[1];
        category = productInfo[2];


        // 1. firebase 에서 해당 제품 찾아서 정보 표시하기.
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference().child("data");
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Product product = datasnapshot.getValue(Product.class);
                    product.setKey(datasnapshot.getKey());


                    // 3-1. 이름과 프랜차이즈 정보가 일치되는 제품을 찾으면 데이터를 표시한다.
                    if(name.equals(product.getName()) && franchise.equals(product.getFranchise())){




                    }

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }

        };
        dbRef.addValueEventListener(mValueEventListener);











    }
}