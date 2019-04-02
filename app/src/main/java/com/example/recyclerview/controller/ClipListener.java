package com.example.recyclerview.controller;

import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Streamer;

import java.util.List;

public class ClipListener implements OnItemClickListener {
    private List<Clip> listclip;
    public ClipListener(List<Clip> list) {
        this.listclip = list;
    }

    @Override
    public void onItemClick(Streamer item) {

    }

    @Override
    public void onItemClick(Clip item) {

    }
}
