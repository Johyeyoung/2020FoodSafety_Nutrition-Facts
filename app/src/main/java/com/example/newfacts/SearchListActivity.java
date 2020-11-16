package com.example.newfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newfacts.menu.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchListActivity extends AppCompatActivity {

    String srch_word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        // 0. 전달된 검색어 받기
        Intent intent = getIntent();
        srch_word = intent.getExtras().getString("content");
        EditText srch_content = (EditText)findViewById(R.id.srch_content);
        srch_content.setText(srch_word);

        // 1. Button Action 처리
        Button back_button =(Button)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // 2. List view 처리 (empty_textView를 이용하여 검색결과 없을 때 표시)
        final ListView listView = findViewById(R.id.product_select_view);
        final TextView empty_textView = findViewById(R.id.empty_text);
        listView.setEmptyView(empty_textView);
        final ArrayList<Product> data = new ArrayList<>();


        // 3. firebase 선언 및 데이터 조회
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference().child("data");
        ValueEventListener mValueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();

                for (DataSnapshot datasnapshot : snapshot.getChildren()) {
                    Product product = datasnapshot.getValue(Product.class);
                    product.setKey(datasnapshot.getKey());


                    // 3-1. 이름으로 데이터 조회 (띄어쓰기 제거)
                    String name = product.getName().replaceAll(" ", "");
                    if(name.contains(srch_word.replaceAll(" ", ""))){
                        data.add(product);
                    }
                    else{  // 3-2. 이름이 일치하지 않으면 상호명으로 조회
                        String franchiseName = product.getFranchise().replaceAll(" ", "");
                        if(srch_word.replaceAll(" ", "").contains(franchiseName)){
                            data.add(product);
                        }
                    }
                }

                final ProductAdapter adapter = new ProductAdapter(data);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("error");
            }

        };
        dbRef.addValueEventListener(mValueEventListener);



        // 4. 아이템을 클릭하면 상세정보 페이지로
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SearchListActivity.this, ProductDetailPage.class);
                intent.putExtra("detail", parent.getItemAtPosition(position).toString());  // 검색어 전달 (스타벅스/나이트로 콜드 브루/임시)
                startActivity(intent);



            }
        });


    }
}