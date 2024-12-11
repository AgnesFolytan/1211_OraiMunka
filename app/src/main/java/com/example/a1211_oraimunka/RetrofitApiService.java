package com.example.a1211_oraimunka;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitApiService {

    // GET all users
    @GET("oSXD9f/item")
    Call<List<Item>> getAllItems();

    // GET user by ID
    @GET("oSXD9f/item/{id}")
    Call<Item> getItemById(@Path("id") int id);

    // POST (create a new user)
    @POST("oSXD9f/item")
    Call<Item> creatItem(@Body Item item);

    // PUT (update a user)
    @PUT("oSXD9f/item/{id}")
    Call<Item> updateItem(@Path("id") int id, @Body Item item);

    // DELETE (delete a user by ID)
    @DELETE("oSXD9f/item/{id}")
    Call<Void> deleteItem(@Path("id") int id);
}
