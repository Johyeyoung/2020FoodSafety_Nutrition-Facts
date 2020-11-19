package com.example.newfacts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class RatingDialog extends DialogFragment implements View.OnClickListener {
    TextView dialog_title, dialog_distance, dialog_assign, dialog_rating;
    Button btn;
    String title, distance, assign, rating;
    private Fragment fragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rating_dialog, container, false);

        fragment = getActivity().getSupportFragmentManager().findFragmentByTag("tag");

        dialog_title = (TextView) view.findViewById(R.id.dialog_title);
        dialog_distance = (TextView) view.findViewById(R.id.dialog_distance);
        dialog_assign = (TextView) view.findViewById(R.id.dialog_assign);
        dialog_rating = (TextView) view.findViewById(R.id.dialog_rating);
        btn = (Button) view.findViewById(R.id.btn);

        dialog_title.setText(title);
        dialog_distance.setText(distance);
        try{
            dialog_assign.append(assign);
            dialog_rating.append(rating);
        }catch (Exception e){
            dialog_assign.append("정보를 가져오는데 시간이 걸립니다!");
            dialog_rating.append("정보를 가져오는데 시간이 걸립니다!");
        }

        btn.setOnClickListener(this);
        return view;

    }

    public RatingDialog(String title, String distance, String assign, String rating) {
        this.title = title;
        this.distance = distance;
        this.assign = assign;
        this.rating = rating;

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn:
                if(fragment != null){
                    DialogFragment dialogFragment = (DialogFragment) fragment;
                    dialogFragment.dismiss();
                    break;
                }
        }
    }

    @Override
    public void onResume() {
        getDialog().getWindow().setLayout(950, 850);
        super.onResume();
    }
}
