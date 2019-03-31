package com.example.recyclerview.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleTwo extends Fragment {


    public static SampleTwo newInstance() {
        SampleTwo frag = new SampleTwo();
        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_sample_two, container, false);

        return result;
    }

}
