package com.example.recyclerview.model.api;

import com.example.recyclerview.model.obj.User;

import java.util.List;

public class RestUserResponse {
    public List<User> getData() {
        return data;
    }

    public void setData(List<User> data) {
        this.data = data;
    }

    private List<User> data;
}
