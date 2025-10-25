package com.konrados.testconstraintlayout.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthApi {

    @POST("/register")
    Call<String> register(@Body User user);

    @POST("/login")
    Call<String> login(@Body User user);

    @GET("/api/questions/getQuestions")
    Call<List<Question>> getQuestions();

    @GET("/api/questions/getByCategory")
    Call<List<Question>> getQuestionsByCategory(@Query("category") String category);




}
