package com.example.recyclerview.controller;

import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Streamer;

public interface OnItemClickListener {
    public void onItemClick(Streamer item);
    public void onItemClick(Clip item);
}
