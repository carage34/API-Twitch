package com.example.recyclerview.view;
import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.recyclerview.R;
import com.example.recyclerview.controller.PageAdapter;
import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {


    private ArrayList<User> user;
    private List<Streamer> streamerList;
    private StreamController sc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        configureViewPagerAndTabs(streamerList);
    }

    private void configureViewPagerAndTabs(List<Streamer> streamerList){
        //Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.viewPager);
        //Set Adapter PageAdapter and glue it together
        PageAdapter pa = new PageAdapter(getSupportFragmentManager());
        pa.setList(streamerList, this);
        pager.setAdapter(pa);

        //Get TabLayout from layout
        TabLayout tabs= (TabLayout)findViewById(R.id.tablayout);
        //Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        //Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    public void startSecondActivity(Game game, User user, Streamer streamer) {
        Intent intent = new Intent(this, UserActivity.class);
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
        this.startActivity(intent);

    }
}