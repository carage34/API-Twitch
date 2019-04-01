package com.example.recyclerview.view;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.recyclerview.R;
import com.example.recyclerview.model.obj.Game;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        ArrayAdapter<Game> arrayAdapter = new ArrayAdapter<Game>(this.getActivity(), android.R.layout.select_dialog_item, MainActivity.gameList);
        AutoCompleteTextView autoCompleteTextView = result.findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(arrayAdapter);
        return result;
    }

}
