package com.example.recyclerview.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.recyclerview.R;
import com.example.recyclerview.controller.StreamController;

public class UserActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        Intent intent = getIntent();
        TextView info = (TextView) findViewById(R.id.info);
        info.setText("Info de " + intent.getStringExtra("name"));

        TextView game = (TextView) findViewById(R.id.game);
        game.setText("Jeu : " + intent.getStringExtra("game"));

        TextView desc = (TextView) findViewById(R.id.desc);
        desc.setText("Description : " + intent.getStringExtra("description"));

        TextView viewers = (TextView) findViewById(R.id.viewers);
        viewers.setText("Nombre de viewer : " + intent.getStringExtra("viewer_count"));

        TextView started = (TextView) findViewById(R.id.started);
        started.setText("Type de streamer  : " + intent.getStringExtra("type"));

        TextView type = (TextView) findViewById(R.id.type);
        type.setText("Diffusion commencé à  : " + intent.getStringExtra("started_at"));

        TextView langage  = (TextView) findViewById(R.id.langage);
        langage.setText("Langage  : " + intent.getStringExtra("langage"));

        TextView title  = (TextView) findViewById(R.id.title);
        title.setText("Title  : " + intent.getStringExtra("title"));

        TextView view_count  = (TextView) findViewById(R.id.total_views);
        view_count.setText("Vue total de la chaine  : " + intent.getStringExtra("view_count"));



    }
}
