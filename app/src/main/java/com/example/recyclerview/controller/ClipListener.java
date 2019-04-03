package com.example.recyclerview.controller;

import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Streamer;
import com.example.recyclerview.view.MainActivity;

import java.util.List;

public class ClipListener implements OnItemClickListener {
    private List<Clip> listclip;
    private MainActivity context;
    public ClipListener(List<Clip> list, MainActivity act) {
        this.listclip = list;
        this.context = act;
    }

    @Override
    public void onItemClick(Streamer item) {

    }

    @Override
    public void onItemClick(Clip item) {
        Toast.makeText(this.context, item.getEmbed_url(), Toast.LENGTH_LONG).show();
        String url = item.getEmbed_url();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        context.startActivity(i);
    }
}
