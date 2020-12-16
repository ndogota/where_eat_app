package com.example.restaurantadvisorapp.network;

import com.example.restaurantadvisorapp.entities.AccessToken;
import com.example.restaurantadvisorapp.entities.RestaurantsResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @POST("register")
    @FormUrlEncoded
    Call<AccessToken> register(@Field("username") String username, @Field("firstname") String firstname, @Field("name") String name, @Field("age") String age, @Field("password") String password, @Field("c_password") String c_password, @Field("email") String email);

    @POST("auth")
    @FormUrlEncoded
    Call<AccessToken> login(@Field("email") String email, @Field("password") String password);

    @GET("restaurants")
    Call<RestaurantsResponse> post();

    @DELETE("restaurant/{id}")
    Call<RestaurantsResponse> delete(@Path("id") int id);
}
