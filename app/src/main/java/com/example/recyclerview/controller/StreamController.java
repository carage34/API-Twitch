package com.example.recyclerview.controller;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.recyclerview.model.api.RestApiManager;
import com.example.recyclerview.model.api.RestGameResponse;
import com.example.recyclerview.model.api.RestStreamResponse;
import com.example.recyclerview.model.api.RestUserResponse;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;
import com.example.recyclerview.view.MainActivity;
import com.example.recyclerview.view.PageFragment;
import com.example.recyclerview.view.StreamFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StreamController {
    private  MainActivity act;
    private List<Streamer> listStreamer;
    private List<User> listUser;
    private List<Game> listGame;
    private ArrayList<User> anotherListUser = new ArrayList<User>();
    private int ii;
    public StreamController(MainActivity activity) {
        this.act = activity;
    }
    public void getStreams() {
        Call<RestStreamResponse> call = RestApiManager.getTwitchAPI().getStream();
        call.enqueue(new Callback<RestStreamResponse>() {
            @Override
            public void onResponse(Call<RestStreamResponse> call, Response<RestStreamResponse> response) {
                RestStreamResponse restStreamResponse = response.body();
                listStreamer = restStreamResponse.getData();
                act.showList(listStreamer);
            }

            @Override
            public void onFailure(Call<RestStreamResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    public void getUsers(String id, int i) {
        ii = i;
        if(i==0) {
            anotherListUser = new ArrayList<>();
        }
        Call<RestUserResponse> call = RestApiManager.getTwitchAPI().getUser(id);
        call.enqueue(new Callback<RestUserResponse>() {
            @Override
            public void onResponse(Call<RestUserResponse> call, Response<RestUserResponse> response) {
                RestUserResponse restUserResponse = response.body();
                if(restUserResponse!=null) {
                    List<User> listUser = restUserResponse.getData();
                    //System.out.println("PLZ : " + listUser.get(0).getProfile_image_url() + " " + listUser.get(0).getLogin());
                    anotherListUser.add(listUser.get(0));
                    listStreamer.get(anotherListUser.size()-1).setProfile_image_url(listUser.get(0).getProfile_image_url());
                    //System.out.println("PLZ : " + ii + "" + listStreamer.get(ii).getProfile_image_url() + " " + listStreamer.get(ii).getUser_name());
                    if(anotherListUser.size()== listStreamer.size()) {
                        System.out.println("PLZE");
                        act.setStreamerList(listStreamer);
                    }
                }
            }
            @Override
            public void onFailure(Call<RestUserResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    public void getUser(String id) {
        Call<RestUserResponse> call = RestApiManager.getTwitchAPI().getUser(id);
        call.enqueue(new Callback<RestUserResponse>() {
            @Override
            public void onResponse(Call<RestUserResponse> call, Response<RestUserResponse> response) {
                RestUserResponse restUserResponse = response.body();
                listUser = restUserResponse.getData();
            }

            @Override
            public void onFailure(Call<RestUserResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    public void getGame(String id) {
        Call<RestGameResponse> call = RestApiManager.getTwitchAPI().getGame(id);
        call.enqueue(new Callback<RestGameResponse>() {
            @Override
            public void onResponse(Call<RestGameResponse> call, Response<RestGameResponse> response) {
                RestGameResponse restGameResponse = response.body();
                listGame = restGameResponse.getData();
                System.out.println("YYY : " + listGame.get(0));
            }

            @Override
            public void onFailure(Call<RestGameResponse> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
        //return listGame.get(0);
    }
}
