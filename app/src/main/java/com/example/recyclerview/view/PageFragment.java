package com.example.recyclerview.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.recyclerview.R;
import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.controller.PageAdapter;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;

import java.util.List;

public class PageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static List<Streamer> listt;
    private static MainActivity act;
    public PageFragment() {}


    // 2 - Method that will create a new instance of PageFragment, and add data to its bundle.
    public static PageFragment newInstance(List<Streamer> list, MainActivity actt) {
        listt = list;
        act = actt;
        PageFragment frag = new PageFragment();

        return(frag);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        System.out.println("SSS");
        View result = inflater.inflate(R.layout.fragment_page, container, false);
        recyclerView = (RecyclerView) result.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), llm.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        MyAdapter myAdapter = new MyAdapter(listt, act);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myAdapter);
        return result;
    }

    public static void setData(List<Streamer> list) {

    }



}