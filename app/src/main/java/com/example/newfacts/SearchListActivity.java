package com.example.newfacts;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.newfacts.menu.Product;

import java.util.ArrayList;

public class SearchListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        // 0. 전달된 검색어 받기
        Intent intent = getIntent();
        String srch_word = intent.getExtras().getString("content");
        EditText srch_content = (EditText)findViewById(R.id.srch_content);
        srch_content.setText(srch_word);
        Log.v("tag", srch_word);
        // 1. Button Action 처리
        Button back_button =(Button)findViewById(R.id.back_button);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchListActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        // 2. List view 처리
        final ArrayList<Product> data = new ArrayList<>();
        data.add(new Product("버블티", "6000", "outer"));
        data.add(new Product("카페라떼", "4500", "top"));
        data.add(new Product("아메리카노", "3500", "bottom"));

        final ProductAdapter adapter = new ProductAdapter(data);
        final ListView listView = findViewById(R.id.product_select_view);
        listView.setAdapter(adapter);



    }
}