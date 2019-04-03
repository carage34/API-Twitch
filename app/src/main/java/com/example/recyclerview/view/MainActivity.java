package com.example.recyclerview.view;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;

import com.example.recyclerview.R;
import com.example.recyclerview.controller.PageAdapter;
import com.example.recyclerview.controller.MyAdapter;
import com.example.recyclerview.controller.StreamController;
import com.example.recyclerview.model.ObjectSerializer;
import com.example.recyclerview.model.api.RestApiManager;
import com.example.recyclerview.model.api.RestClipResponse;
import com.example.recyclerview.model.api.RestGameResponse;
import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {


    private ArrayList<User> user;
    private List<Streamer> streamerList;
    private StreamController sc;
    private ArrayList<String> gameIdList = new ArrayList<String>();
    public static List<Game> gameList;
    public static SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.widget.Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("API Twitch");
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        sc = new StreamController(this);
        sc.getStreams();

    }
    public void showList(List<Streamer> list, boolean ok) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        if(ok) {
            for(int i=0;i<list.size();i++) {
                this.gameIdList.add(list.get(i).getGame_id());
                //System.out.println("AAA" + list.get(i).getGame_id());
                //System.out.println("ID : " + list.get(i).getId());
                sc.getUsers(list.get(i).getUser_id(), i);
            }
        }
    }

    public void fetchGame() {
        RestGameResponse resultGame = null;
        Call<RestGameResponse> callGame = RestApiManager.getTwitchAPI().getTopGame("100");
        try {
            resultGame = callGame.execute().body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        gameList= resultGame.getData();
    }

    public void setStreamerList(List<Streamer> streamerList) {
        SharedPreferences.Editor editor = sharedPref.edit();
        ArrayList<Streamer> arrlistofOptions = new ArrayList<>(streamerList);
        try {
            editor.putString("liststreamer", ObjectSerializer.serialize(arrlistofOptions));
        } catch (IOException e) {
            e.printStackTrace();
        }
        editor.commit();
        fetchGame();
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
        this.startActivity(intent,  ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

    }
}