package com.example.recyclerview.model.api;

import com.example.recyclerview.model.obj.Clip;
import com.example.recyclerview.model.obj.Game;

import java.util.List;

public class RestClipResponse {
    public List<Clip> getData() {
        return data;
    }

    public void setData(List<Clip> data) {
        this.data = data;
    }

    private List<Clip> data;

}
