package com.newfact.newfacts;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.newfact.newfacts.menu.Product;
import com.newfact.newfacts.productDetail.Myadapter;
import com.newfact.newfacts.productDetail.SearchListActivity;

import java.util.ArrayList;

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



        // seekbar 설정
        Integer[] Rid_Seek_bar = {
                R.id.KcalBar, R.id.FatBar, R.id.ProteinBar, R.id.SodiumBar, R.id.SugarBar,
                R.id.CaffeineBar};
        final SeekBar seekbar[] = new SeekBar[6];

        Integer[] Rid_Seek_Text = {
                R.id.KcalAmount, R.id.FatAmount, R.id.ProteinAmount, R.id.SodiumAmount, R.id.SugarAmount,
                R.id.CaffeineAmount};
        final TextView seek_text[] = new TextView[6];
        final String[] unit = {"kcal", "g", "g", "mg", "g", "mg"}; // 단위


        for(int i=0;i<=5; i++){

            seek_text[i] = (TextView)view.findViewById(Rid_Seek_Text[i]);
            seekbar[i] = (SeekBar)view.findViewById(Rid_Seek_bar[i]);
            final int finalI = i;
            seekbar[i].setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                public void onStopTrackingTouch(SeekBar seekBar) {
                }
                public void onStartTrackingTouch(SeekBar seekBar) {
                }
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    seek_text[finalI].setText(progress + unit[finalI]);
                }
            });


        }

        // 성분검색 버튼
        Button SearchBtn_nut = (Button)view.findViewById(R.id.button2);
        SearchBtn_nut.setOnClickListener(new View.OnClickListener() {
            @Override // 버튼을 누르면 조회창으로 넘어가기
            public void onClick(View view) {
                String nutrition_content = "";
                for(int i=0;i<=5; i++){
                    nutrition_content += seekbar[i].getProgress()+"/";
                }
                nutrition_content.replaceAll("[^0-9/]","");
                Intent intent = new Intent(getActivity(), SearchListActivity.class);
                intent.putExtra("content", nutrition_content);  // 검색어 전달
                startActivity(intent);


            }
        });


        // 추천음료 리사이클러뷰
        try {
            final RecyclerView mRecyclerView = view.findViewById(R.id.recycler_view_recommend);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(mLayoutManager);
            final ArrayList<Product> data = new ArrayList<>();
            Myadapter adapter = new Myadapter(data);
            mRecyclerView.setAdapter(adapter);
            data.add(new Product("starbucks", "아메리카노", "data:image/jpeg;base64,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"));
            data.add(new Product("starbucks", "아메리카노", ""));
            data.add(new Product("starbucks", "아메리카노", ""));
        }
        catch (Exception e){
            e.printStackTrace();
        }



        return view;
    }
}
