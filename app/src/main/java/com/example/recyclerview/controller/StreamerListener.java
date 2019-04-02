package com.example.recyclerview.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.support.v4.app.Fragment;

import com.example.recyclerview.model.api.RestApiManager;
import com.example.recyclerview.model.api.RestGameResponse;
import com.example.recyclerview.model.api.RestUserResponse;
import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Game;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.model.obj.User;
import com.example.recyclerview.view.MainActivity;
import com.example.recyclerview.view.PageFragment;
import com.example.recyclerview.view.StreamFragment;
import com.example.recyclerview.view.UserActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class StreamerListener implements OnItemClickListener{
    private List<Streamer> listStreamer;
    private StreamController st;
    private MainActivity main;
    public StreamerListener(List<Streamer> list, MainActivity pf) {
        this.listStreamer = list;
        main = pf;
    }

    @Override
    public void onItemClick(Streamer item) {
        System.out.println("VVV" + item.getUser_name());
        RestUserResponse resultUser = null;
        RestGameResponse resultGame = null;
        Call<RestUserResponse> callUser = RestApiManager.getTwitchAPI().getUser(item.getUser_id());
        Call<RestGameResponse> callGame = RestApiManager.getTwitchAPI().getGame(item.getGame_id());
            try {
                resultUser = callUser.execute().body();
            } catch(IOException e) {
                System.out.println(e.getMessage());
            }
            try {
                resultGame = callGame.execute().body();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            //if((resultGame!=null)&&(resultUser!=null)) {
                main.startSecondActivity(resultGame.getData().get(0), resultUser.getData().get(0), item);
            //} else {
              //  System.out.println("SHIIIIT");
            //}




    }

    @Override
    public void onItemClick(Clip item) {

    }
}
