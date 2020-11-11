package com.example.newfacts;

import android.content.Intent;
import android.os.Bundle;
<<<<<<< HEAD
=======
import android.util.Log;
>>>>>>> bb3d177f0628c0b2149add41cadc603538985be1
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
<<<<<<< HEAD
=======
import android.widget.EditText;
>>>>>>> bb3d177f0628c0b2149add41cadc603538985be1

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
<<<<<<< HEAD

=======
    EditText srch_content;
>>>>>>> bb3d177f0628c0b2149add41cadc603538985be1
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, null);

<<<<<<< HEAD
=======
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
>>>>>>> bb3d177f0628c0b2149add41cadc603538985be1

        return view;
    }
}
