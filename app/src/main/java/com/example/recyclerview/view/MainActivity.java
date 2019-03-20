package com.example.recyclerview.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.R;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private StreamController sc;
    private ArrayList<User> user;
    private List<Streamer> streamerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        sc = new StreamController(this);
        sc.getStreams();

    }
    public void showList(List<Streamer> list) {
        boolean ok = false;
        for(int i=0;i<list.size();i++) {
            System.out.println("ID : " + list.get(i).getId());
            if(i==list.size()-1)
                ok = true;
            sc.getUsers(list.get(i).getUser_id(), i);
        }

    }

    public void setStreamerList(List<Streamer> streamerList) {
        System.out.println("RRR: " + streamerList.size());
        mAdapter = new MyAdapter(streamerList, this, this.sc, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Streamer item) {
                Toast.makeText(getBaseContext(), item.getUser_name(), Toast.LENGTH_LONG).show();
            }
        });
        recyclerView.setAdapter(mAdapter);
    }
}