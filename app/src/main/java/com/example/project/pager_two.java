package com.example.project;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
public class pager_two extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v= inflater.inflate(R.layout.activity_pager_two, container, false);
        //TextView textView=v.findViewById(R.id.text);
        //textView.setText("Second Fragment");
        return v;
    }
}