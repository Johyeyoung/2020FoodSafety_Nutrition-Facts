package com.example.newfacts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class FragmentHealthInfo extends Fragment {
    String [] sex_ = {"남성", "여성"};
    String [] allergy = {"우유 알레르기",
                        "대두 알레르기",
                        "복숭아 알레르기",
                        "토마토 알레르기",
                        "오징어 알레르기"};
    UserInfo userInfo;

    DatabaseReference mDBReference = null;
    HashMap<String, Object> childUpdates = null;
    Map<String, Object> userValue = null;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    String user_id = user.getUid();
    String user_sex ="";
    String user_age = "";
    String user_height="";
    String user_weight="";
    Boolean user_milk_allergy=false;
    Boolean user_soybean_allergy=false;
    Boolean user_peach_allergy=false;
    Boolean user_tomato_allergy=false;
    Boolean user_squid_allergy=false;

    Button buttonSaveUserInfo;
    EditText editTextAge;
    EditText editTextHeight;
    EditText editTextWeight;

    public FragmentHealthInfo() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_health_info, container, false);

        // spinner: 성별 -> {' ', 남성', '여성'}
        // View view = inflater.inflate(R.layout.fragment_health_info, container, false);
        Spinner spinner = (Spinner)layout.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, sex_);
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                user_sex = sex_[position];
            }

            @Override // 아무것도 선택 안 되었을때
            public void onNothingSelected(AdapterView<?> parent) {
                user_sex = "";
            }
        });
        // 스피너 끝

        // *** 알레르기 정보 시작
        ListView listview;

        final CustomChoiceListViewAdapter adapter = new CustomChoiceListViewAdapter();

        listview = (ListView)layout.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        for(int i=0;i<allergy.length;i++){
            adapter.addItem(allergy[i]);
        }

//
//        adapter.addItem("우유 알레르기 ");
//        adapter.addItem("대두 알레르기 ");
//        adapter.addItem("복숭아 알레르기 ");
//        adapter.addItem("토마토 알레르기 ");
//        adapter.addItem("오징어 알레르기 ");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        user_milk_allergy = true;
                        break;
                    case 1:
                        user_soybean_allergy = true;
                        break;
                    case 2:
                        user_peach_allergy = true;
                        break;
                    case 3:
                        user_tomato_allergy = true;
                        break;
                    case 4:
                        user_squid_allergy =true;
                        break;
                }
            }
        });


        // *** 알레르기 정보 끝


        // 이제 파이어베이스에 데이터를 저장한다
        // 파이어베이스 코드 부분 시작
        buttonSaveUserInfo = (Button)layout.findViewById(R.id.buttonSaveUserInfo);
        buttonSaveUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 나이
                editTextAge = (EditText)layout.findViewById(R.id.editTextAge);
                if(editTextAge.getText().toString().length()==0){
                    user_age = "";
                } else{
                    user_age = editTextAge.getText().toString();
                }
                // 나이 끝

                // 키
                editTextHeight = (EditText)layout.findViewById(R.id.editTextHeight);
                if(editTextHeight.getText().toString().length()==0){
                    user_height = "";
                } else{
                    user_height = editTextHeight.getText().toString();

                }
                // 키 끝

                // 몸무게
                editTextWeight = (EditText)layout.findViewById(R.id.editTextWeight);
                if(editTextWeight.getText().toString().length()==0){
                    user_weight = "";
                } else{
                    user_weight = editTextWeight.getText().toString();

                }
                // 몸무게 끝
                mDBReference = FirebaseDatabase.getInstance().getReference();
                childUpdates = new HashMap<>();
                userInfo = new UserInfo(user_id, user_sex, user_age, user_height, user_weight,
                        user_milk_allergy, user_soybean_allergy, user_peach_allergy, user_tomato_allergy, user_squid_allergy);
                userValue = userInfo.toMap();
                childUpdates.put("/User/"+user_id+"/UserInfo" , userValue);
                mDBReference.updateChildren(childUpdates);
            }
        });
        // 파이어베이스 코드 부분 끝

        return layout;
    }
}