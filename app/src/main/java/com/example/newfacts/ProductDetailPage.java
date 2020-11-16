package com.example.newfacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.newfacts.menu.Product;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductDetailPage extends AppCompatActivity {


    public String franchise;
    public String name;
    public String eng;
    public String category;
    public String desc;
    public String allergy;
    public String nutrition;
    public String pic;
    public String volume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail_page);

        // 0. 전달된 검색어 받기 (스타벅스/나이트로 콜드 브루/임시)

        Intent intent = getIntent();
        String product = intent.getExtras().getString("detail");
        String[] productInfo = product.split("-");
        franchise = productInfo[0];
        name = productInfo[1];
        eng = productInfo[2];
        category = productInfo[3];
        desc = productInfo[4];
        nutrition = productInfo[5];
        allergy = productInfo[6];
        pic = productInfo[7];
        volume = productInfo[8];



        // 화면 구성
        TextView Franchise = (TextView) findViewById(R.id.Franchise);
        Franchise.setText(franchise);
        TextView Name = (TextView) findViewById(R.id.Name);
        Name.setText(name);
        TextView NameEnglish = (TextView) findViewById(R.id.NameEnglish);
       // Franchise.setText(nameEnglish);
        TextView Desc = (TextView) findViewById(R.id.Desc);
        Desc.setText(desc);
        TextView Volume = (TextView) findViewById(R.id.Volume);
        Volume.setText("총 내용량 "+ volume);

        // 성분표 구성 (Amount)
        Integer[] Rid_Text = {
                R.id.KcalAmount, R.id.FatAmount, R.id.ProteinAmount, R.id.SodiumAmount, R.id.SugarAmount,
                R.id.CaffeineAmount};
        String[] nut_kfpSsc = nutrition.split("/");
        String[] unit = {"kcal", "g", "g", "mg", "g", "mg"}; // 단위
        TextView nut_text[] = new TextView[6];

        // 성분표 구성 (Rating)
        Integer[] Rid_Text_rating = {
                R.id.KcalRate, R.id.FatRate, R.id.ProteinRate, R.id.SodiumRate, R.id.SugarRate,
                R.id.CaffeineRate};
        int[] total_kfpSsc = {2000, 54, 55, 2000, 100, 400}; // 1일 최대 섭취량
        TextView rate_text[] = new TextView[6];


        Integer[] Rid_Progress_bar = {
                R.id.KcalBar, R.id.FatBar, R.id.ProteinBar, R.id.SodiumBar, R.id.SugarBar,
                R.id.CaffeineBar};
        ProgressBar progress[] = new ProgressBar[6];


        // 입력하기
        for(int i=0;i<=5; i++){
            nut_text[i] = (TextView) findViewById(Rid_Text[i]);
            nut_text[i].setText(nut_kfpSsc[i]+unit[i]);

            rate_text[i] = (TextView) findViewById(Rid_Text_rating[i]);
            int rate = Integer.parseInt(nut_kfpSsc[i])*100/total_kfpSsc[i];
            rate_text[i].setText(Integer.toString(rate)+'%');

            progress[i] = (ProgressBar) findViewById(Rid_Progress_bar[i]);
            progress[i].setProgress(rate);
        }







    }
}