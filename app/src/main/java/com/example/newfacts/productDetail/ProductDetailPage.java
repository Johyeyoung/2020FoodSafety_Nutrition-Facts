package com.example.newfacts.productDetail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newfacts.R;
import com.example.newfacts.menu.Product;
import com.example.newfacts.menu.UserInfo;
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


        // Glide로 이미지 표시하기
        ImageView ivImage = findViewById(R.id.imageView);
        Glide.with(this).load(pic).into(ivImage);
        TextView Franchise = (TextView) findViewById(R.id.Franchise);
        Franchise.setText(franchise);
        TextView Name = (TextView) findViewById(R.id.Name);
        Name.setText(name);
        TextView NameEnglish = (TextView) findViewById(R.id.NameEnglish);
        Franchise.setText(eng);
        TextView Desc = (TextView) findViewById(R.id.Desc);
        Desc.setText(desc);
        TextView Volume = (TextView) findViewById(R.id.Volume);
        Volume.setText("총 내용량 "+ volume);





        // 성분표 구성TextView (Amount)
        Integer[] Rid_Text = {
                R.id.KcalAmount, R.id.FatAmount, R.id.ProteinAmount, R.id.SodiumAmount, R.id.SugarAmount,
                R.id.CaffeineAmount};
        String[] nut_kfpSsc = nutrition.split("/");
        String[] unit = {"kcal", "g", "g", "mg", "g", "mg"}; // 단위
        TextView nut_text[] = new TextView[6];

        // 성분표 구성TextView (Rating)
        Integer[] Rid_Text_rating = {
                R.id.KcalRate, R.id.FatRate, R.id.ProteinRate, R.id.SodiumRate, R.id.SugarRate,
                R.id.CaffeineRate};
        int[] total_kfpSsc = {2000, 54, 55, 2000, 100, 400}; // 1일 최대 섭취량
        TextView rate_text[] = new TextView[6];

        // 성분표 구성 progress_bar (Rating)
        Integer[] Rid_Progress_bar = {
                R.id.KcalBar, R.id.FatBar, R.id.ProteinBar, R.id.SodiumBar, R.id.SugarBar,
                R.id.CaffeineBar};
        ProgressBar progress[] = new ProgressBar[6];

        String[] allergy_mspotw = allergy.split("/"); //알레르기
        String[] allergyInfo = {"우유", "대두", "복숭아", "오징어", "토마토", "밀"};
        String allergy_result = "";




        // 고객의 정보를 바탕으로 필터링
        UserInfo user = new UserInfo();
       // String[] custm_Nutrion = user.nutrition.split("/"); // 설정한 성분 기준치
       // String[] custm_allergy = user.allergy.split("/");   // 설정한 알러지반응 식품
        String[] custm_Nutrion = nutrition.split("/"); // 설정한 성분 기준치


        int notice_flag = 0;

        // 화면에 나타내기
        for(int i=0;i<=5; i++){
            nut_text[i] = (TextView) findViewById(Rid_Text[i]);
            nut_text[i].setText(nut_kfpSsc[i]+unit[i]);

            rate_text[i] = (TextView) findViewById(Rid_Text_rating[i]);
            int rate = Integer.parseInt(nut_kfpSsc[i])*100/total_kfpSsc[i];
            rate_text[i].setText(Integer.toString(rate)+'%');

            // 고객설정값보다 높으면 change color of progressbar
            progress[i] = (ProgressBar) findViewById(Rid_Progress_bar[i]);
            if(Integer.parseInt(nut_kfpSsc[i])>= Integer.parseInt(custm_Nutrion[i])){
                progress[i].getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                notice_flag = 1;
            }
            progress[i].setProgress(rate);

            //알레르기
            if(allergy_mspotw[i].equals("1")){
                allergy_result = allergy_result + allergyInfo[i];
            }

        }


        // 알레르기 정보
        TextView Allergy = (TextView) findViewById(R.id.Allergy);
        if(allergy_result.equals("")){
            Allergy.setText(" 없음");
        }
        else{
            Allergy.setText(allergy_result);
            notice_flag = (notice_flag == 1)? 3: 2; // 만약 성분주의가 이미 있었다면 _a_n
        }


        Integer[] Rid_Notice = {R.drawable.notice_n, R.drawable.notice_a, R.drawable.notice_a_n };
        ImageView Notice = findViewById(R.id.Notice);
        if(notice_flag != 0){
            Notice.setImageResource(Rid_Notice[notice_flag-1]);
        }





    }
}