package com.example.recyclerview.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiManager
{
    private static TwitchServiceAPI twitchApi = null;
    public static final String BASE_URL = "https://api.twitch.tv/helix/";
    public static final String STREAM_URL = BASE_URL + "/streams/";

    public static TwitchServiceAPI getTwitchAPI()
    {
        if(twitchApi == null)
        {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
            twitchApi = retrofit.create(TwitchServiceAPI.class);
        }
        return twitchApi;
    }
}

