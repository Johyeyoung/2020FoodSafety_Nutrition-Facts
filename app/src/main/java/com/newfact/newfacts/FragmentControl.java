package com.newfact.newfacts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.renderscript.Sampler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.newfact.newfacts.menu.UserInfo;

import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.Map;


public class FragmentControl extends Fragment {

    TextView calorie_textView;
    TextView fat_textView;
    TextView sugar_textView;
    TextView caffeine_textView;

    SeekBar calorie_seekBar;
    SeekBar fat_seekBar;
    SeekBar sugar_seekBar;
    SeekBar caffeine_seekBar;

    Button save_control_button;
    String nutrition;
    DatabaseReference mDBReference = FirebaseDatabase.getInstance().getReference(); // 파이어베이스
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String user_id = user.getUid();

    public FragmentControl() {}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final LinearLayout layout =  (LinearLayout)inflater.inflate(R.layout.fragment_control, container, false);
        // 정의
        calorie_textView = (TextView)layout.findViewById(R.id.textViewCalorie);
        fat_textView = (TextView)layout.findViewById(R.id.textViewFat);
        sugar_textView = (TextView)layout.findViewById(R.id.textViewSugar);
        caffeine_textView = (TextView)layout.findViewById(R.id.textViewCaffeine);

        calorie_seekBar = (SeekBar)layout.findViewById(R.id.seekBarCalorie);
        fat_seekBar = (SeekBar)layout.findViewById(R.id.seekBarFat);
        sugar_seekBar = (SeekBar)layout.findViewById(R.id.seekBarSugar);
        caffeine_seekBar = (SeekBar)layout.findViewById(R.id.seekBarCaffeine);
        save_control_button = (Button)layout.findViewById(R.id.buttonSaveControl);
        //

        // seekBar
        calorie_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                calorie_textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        fat_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                fat_textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        sugar_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sugar_textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
        caffeine_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                caffeine_textView.setText(String.valueOf(progress));
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        //

        UserInfo userInfo = UserInfo.getInstance();
        nutrition = userInfo.getNutrition();
        if(nutrition!=null){
            String [] user_nutrition_data = nutrition.split("/");
            calorie_seekBar.setProgress(Integer.parseInt(user_nutrition_data[0]));
            fat_seekBar.setProgress(Integer.parseInt(user_nutrition_data[1]));
            sugar_seekBar.setProgress(Integer.parseInt(user_nutrition_data[3]));
            caffeine_seekBar.setProgress(Integer.parseInt(user_nutrition_data[5]));
        }
        // 변경한 데이터들 파이어베이스에 저장
        save_control_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nutrition = calorie_textView.getText()+"/"+
                        fat_textView.getText()+"/0/"+
                        sugar_textView.getText()+"/0/"+
                        caffeine_textView.getText();
                mDBReference.child("/UserInfo/"+user_id).child("nutrition").setValue(nutrition);

                Toast.makeText(getActivity(), "저장에 성공했습니다", Toast.LENGTH_SHORT).show();

            }
        });
        return layout;
    }
}