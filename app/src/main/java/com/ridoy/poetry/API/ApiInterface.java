package com.ridoy.poetry.API;

import com.ridoy.poetry.Responses.DeletePoetry;
import com.ridoy.poetry.Responses.GetPoetry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("read.php")
    Call<GetPoetry> getPoetry();

    @FormUrlEncoded
    @POST("delete.php")
    Call<DeletePoetry> deletePoetry(
            @Field("id") String id
    );
}
