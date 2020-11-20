package com.newfact.newfacts;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.newfact.newfacts.CustomerData.FragmentHealthInfo;
import com.newfact.newfacts.CustomerData.FragmentMyinfo;
import com.newfact.newfacts.menu.UserInfo;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity{

    // 프레그먼트
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentMain fragmentMain = new FragmentMain();
    private FragmentCategory fragmentCategory = new FragmentCategory();
    private FragmentLocation fragmentLocation = new FragmentLocation();
    private FragmentMyinfo fragmentMyinfo = new FragmentMyinfo();


    // 민정이 코드
    private FragmentHealthInfo fragmentHealthInfo = new FragmentHealthInfo();
    private FragmentControl fragmentControl = new FragmentControl();
    DatabaseReference mDBReference = null;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    UserInfo userInfo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // 민정이코드 시작
        // 로그인 후 데이터 가져오기
        userInfo = UserInfo.getInstance();
        String user_id = user.getUid();
        mDBReference = FirebaseDatabase.getInstance().getReference();// 파이어베이스 참조
        final DatabaseReference userInfoRef = mDBReference.child("UserInfo").child(user_id);
        // 파이어베이스에서 유저 데이터 가져오기
        userInfoRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.child("age").getValue()!=null){
                    String age = dataSnapshot.child("age").getValue().toString();
                    userInfo.setAge(age);
                }
                if(dataSnapshot.child("sex").getValue()!=null){
                    String sex = dataSnapshot.child("sex").getValue().toString();
                    userInfo.setSex(sex);
                }
                if(dataSnapshot.child("height").getValue()!=null){
                    String height = dataSnapshot.child("height").getValue().toString();
                    userInfo.setHeight(height);
                }
                if(dataSnapshot.child("weight").getValue()!=null){
                    String weight = dataSnapshot.child("weight").getValue().toString();

                    userInfo.setWeight(weight);
                }
                if (dataSnapshot.child("allergy").getValue() != null) {
                    Log.d("firebase has data", "test");
                    String allergy = dataSnapshot.child("allergy").getValue().toString();
                    userInfo.setAllergy(allergy);
                }
                if (dataSnapshot.child("nutrition").getValue()!=null){
                    String nutrition = dataSnapshot.child("nutrition").getValue().toString();
                    userInfo.setNutrition(nutrition);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });  /// 민정이 코드 끝






        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());



    }








    // 하단 네비게이션 bar
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            switch(menuItem.getItemId())
            {
                case R.id.Main:
                    transaction.replace(R.id.frameLayout, fragmentMain).commitAllowingStateLoss();
                    break;
                case R.id.Category:
                    transaction.replace(R.id.frameLayout, fragmentCategory).commitAllowingStateLoss();
                    break;
                case R.id.Location:
                    transaction.replace(R.id.frameLayout, fragmentLocation).commitAllowingStateLoss();
                    break;
                case R.id.MyInfo:
                    transaction.replace(R.id.frameLayout, fragmentMyinfo).commitAllowingStateLoss();
                    break;
            }
            return true;
        }
    }





    // 민정이꺼
    public void onFragmentChange(int index){
        if(index ==0){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentHealthInfo).commit();
        }
        else if (index ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, fragmentControl).commit();
        }
    }



}