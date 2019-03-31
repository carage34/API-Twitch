package com.example.recyclerview.view;


import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerview.R;
import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreamFragment extends Fragment {



    public StreamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_stream, container, false);
        return rootView;
    }


}
