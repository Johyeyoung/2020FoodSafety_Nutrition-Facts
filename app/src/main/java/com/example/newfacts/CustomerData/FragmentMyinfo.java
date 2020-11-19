package com.example.newfacts.CustomerData;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newfacts.CustomerData.LoginActivity;
import com.example.newfacts.MainActivity;
import com.example.newfacts.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class FragmentMyinfo extends Fragment {
    DatabaseReference mDBReference = null;

    // TODO: Rename and change types of parameters
    String [] list_menu = { "건강 정보", "영양 성분 수치 제한하기","로그아웃"};
    public FragmentMyinfo() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.fragment_myinfo, container, false);

        mDBReference = FirebaseDatabase.getInstance().getReference();// 파이어베이스 참조
//        final DatabaseReference userInfoRef = mDBReference.child("UserInfo").child(user_id);
//        userInfoRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if (dataSnapshot.child("allergy").getValue() != null) {
//                    Log.d("firebase has data", "test");
//                    String allergy = dataSnapshot.child("allergy").getValue().toString();
//                    String[] user_allergy_data = allergy.split("/");
//                    Log.d("test check allergy", dataSnapshot.child("allergy").getValue().toString());
//                    for(int i=0;i<5;i++){
//                        if(user_allergy_data[i].equals("1")){
//                            listview.setItemChecked(i, true);
//                        }
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {}
//        });
        EditText editText = (EditText)layout.findViewById(R.id.editTextNickName);
        ListView listView = (ListView)layout.findViewById(R.id.listView_Myinfo);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                list_menu
        );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ((MainActivity)getActivity()).onFragmentChange(position);
                }
                else if(position ==1 ){
                    ((MainActivity)getActivity()).onFragmentChange(position);
                }
                else if(position ==2){
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(getActivity(),"로그아웃",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        listView.setAdapter(listViewAdapter);

        return layout;
    }
}