package com.geoideas.spooch_mobile.http;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SpoochApi {

    @POST("login")
    Call<ReturnedToken> login(@Body UserLogin user);
    @POST("users")
    Call<Void> registerUser(@Body RegisteredUser rUser);
    @POST("events")
    Call<ReturnedId> postEvent(@Header("Authorization") String authHeader, @Body EventDTO event);

}
