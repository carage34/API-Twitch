package com.example.recyclerview.model.api;

import com.example.recyclerview.model.obj.Game;

import java.util.List;

public class RestGameResponse {
    public List<Game> getData() {
        return data;
    }

    public void setData(List<Game> data) {
        this.data = data;
    }

    private List<Game> data;

}
