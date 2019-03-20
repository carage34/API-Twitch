package com.example.recyclerview.model.api;

import com.example.recyclerview.model.obj.Streamer;

import java.util.List;

public class RestStreamResponse {
    public List<Streamer> getData() {
        return data;
    }

    public void setData(List<Streamer> data) {
        this.data = data;
    }

    private List<com.example.recyclerview.model.obj.Streamer> data;

}
