package com.example.newfacts.productDetail;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newfacts.R;
import com.example.newfacts.menu.UserInfo;


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
        ImageView noticeImage = (ImageView) findViewById(R.id.noticeImg);
        ImageView ServiceImage = (ImageView) findViewById(R.id.serviceImg);
        ServiceImage.setImageResource(R.drawable.service_info);


        // Glide로 이미지 표시하기
        ImageView ivImage = findViewById(R.id.imageView);
        Glide.with(this).load(pic).into(ivImage);
        TextView Franchise = (TextView) findViewById(R.id.Franchise);
        Franchise.setText(franchise);
        TextView Name = (TextView) findViewById(R.id.Name);
        Name.setText(name);
        TextView NameEnglish = (TextView) findViewById(R.id.NameEnglish);
        NameEnglish.setText(eng);
        TextView Desc = (TextView) findViewById(R.id.Desc);
        Desc.setText(desc);
        TextView Volume = (TextView) findViewById(R.id.Volume);
        Volume.setText("총 내용량 "+ volume);


        // 고객의 정보
        UserInfo userInfo = new UserInfo();
        String[] custm_allergy =userInfo.getAllergy().split("/");
        String[] custm_nutrition =userInfo.getNutrition().split("/");
        System.out.println(custm_allergy);
        System.out.println(custm_nutrition);

        // String[] custm_nutrition =nutrition.split("/");

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


        String[] allergys = allergy.split("/");
        String[] allergy_name = {"우유", "대두", "복숭아", "오징어", "토마토", "밀"};
        String allergy_Info = "";


        int flag = 0; // 주의성분 사진용

        // 입력하기
        for(int i=0;i<=5; i++){
            nut_text[i] = (TextView) findViewById(Rid_Text[i]);
            nut_text[i].setText(nut_kfpSsc[i]+unit[i]);

            rate_text[i] = (TextView) findViewById(Rid_Text_rating[i]);
            int rate = Integer.parseInt(nut_kfpSsc[i])*100/total_kfpSsc[i];
            rate_text[i].setText(Integer.toString(rate)+'%');


            progress[i] = (ProgressBar) findViewById(Rid_Progress_bar[i]);
            if(Integer.parseInt(custm_nutrition[i]) < Integer.parseInt(nut_kfpSsc[i])){
                progress[i].getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
                flag = 1;// 성분주의마크
            }
            progress[i].setProgress(rate);

            // 알러지정보
            if(allergys[i].equals("1")){
                allergy_Info += (allergy_name[i]+ " ");
                flag = (flag == 1)?3:2; // 2번은 알러지만, 3번은 성분/알러지
            }

        }

        TextView Allergy_Info = (TextView) findViewById(R.id.Allergy); // 알러지 정보표시

        // 고객맞춤 마크표시
        if(allergy_Info.equals("")){
            Allergy_Info.setText("  없음");
        }
        Allergy_Info.setText(allergy_Info);
        Integer[] Rid_noticeImage = {R.drawable.notice_n, R.drawable.notice_a, R.drawable.notice_n_a};
        if(flag != 0){
            noticeImage.setImageResource(Rid_noticeImage[flag-1]);
        }






    }
}