package com.example.recyclerview.model.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TwitchServiceAPI {
    public static final String ENDPOINT = "https://api.twitch.tv/helix/";
    @Headers({
            "Client-ID: 1utxz63kdn8vmccch4stxn3payttdi"
    })
    @GET("streams")
    Call<RestStreamResponse> getStream();

    @Headers({
            "Client-ID: 1utxz63kdn8vmccch4stxn3payttdi"
    })
    @GET("users")
    Call<RestUserResponse> getUser(@Query("id")String id);

    @Headers({
            "Client-ID: 1utxz63kdn8vmccch4stxn3payttdi"
    })
    @GET("games")
    Call<RestGameResponse> getGame(@Query("id")String id);

}
