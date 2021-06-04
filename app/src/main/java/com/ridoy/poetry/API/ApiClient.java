package com.ridoy.poetry.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static Retrofit retrofit=null;

    public static ApiInterface getClient(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://100.87.184.74/Poetry/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }
}
