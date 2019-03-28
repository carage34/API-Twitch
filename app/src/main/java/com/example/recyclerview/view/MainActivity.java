package com.example.recyclerview.view;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.R;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private LinearLayoutManager layoutManager;
    private StreamController sc;
    private ArrayList<User> user;
    private List<Streamer> streamerList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
                // show the given tab
            }

            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // hide the given tab
            }

            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
                // probably ignore this event
            }
        };

        for (int i = 0; i < 3; i++) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText("Tab " + (i + 1))
            .setTabListener(tabListener));
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        sc = new StreamController(this);
        sc.getStreams();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
        mAdapter = new MyAdapter(streamerList, this, this.sc);
        recyclerView.setAdapter(mAdapter);
    }

    public void startSecondActivity(Game game, User user, Streamer streamer) {
        Intent intent = new Intent(MainActivity.this, UserActivity.class);
        intent.putExtra("name", streamer.getUser_name());
        intent.putExtra("description", user.getDescription());
        intent.putExtra("view_count", user.getView_count());
        intent.putExtra("profile_image", user.getProfile_image_url());
        intent.putExtra("game", game.getName());
        intent.putExtra("game_image", game.getBox_art_url());
        intent.putExtra("title", streamer.getTitle());
        intent.putExtra("viewer_count", streamer.getViewer_count());
        intent.putExtra("langage", streamer.getLanguage());
        intent.putExtra("type", streamer.getType());
        intent.putExtra("started_at", streamer.getStarted_at());
        intent.putExtra("profil_image", streamer.getProfile_image_url());
        intent.putExtra("offline_image", user.getOffline_image_url());
        MainActivity.this.startActivity(intent);

    }
}