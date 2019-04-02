package com.example.recyclerview.view;


import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.recyclerview.R;
import com.example.recyclerview.controller.ClipAdapter;
import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SampleTwo extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static List<Clip> list;
    private static MainActivity act;

    public static SampleTwo newInstance(MainActivity actt) {
        act = actt;
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
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Game selected = (Game) parent.getItemAtPosition(position);
                int pos = MainActivity.gameList.indexOf(selected);
                StreamController sc = new StreamController();
                list = sc.getClip(MainActivity.gameList.get(pos).getId());
                mAdapter = new ClipAdapter(list, act);
                recyclerView.setAdapter(mAdapter);

                Toast.makeText(getContext(), MainActivity.gameList.get(pos).getId(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView = (RecyclerView) result.findViewById(R.id.clip_recyclerview);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        //ClipAdapter myAdapter = new ClipAdapter(listt, act);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(myAdapter);
        recyclerView.setHasFixedSize(true);

        return result;
    }

}
