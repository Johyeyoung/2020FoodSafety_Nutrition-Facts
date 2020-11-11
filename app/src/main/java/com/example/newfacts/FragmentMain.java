package com.example.newfacts;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    EditText srch_content;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, null);

        srch_content = (EditText)view.findViewById(R.id.srch_content);


        Button SearchBtn = (Button)view.findViewById(R.id.button);
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override // 버튼을 누르면 조회창으로 넘어가기
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SearchListActivity.class);
                intent.putExtra("content", srch_content.getText().toString());  // 검색어 전달
                startActivity(intent);


            }
        });

        return view;
    }
}
