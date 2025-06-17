package com.example.app_crud_api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface EndPoint {
    @GET("/Pessoas/")
    fun get() : Call<ArrayList<UserModel>>

    @GET("/Pessoas/{id}")
    fun getPessoaId(@Path("id") id: Int) : Call<UserModel>

    @POST("/Pessoas/")
    fun post() : Call<ArrayList<UserModel>>

    @PUT("/Pessoas/{id}")
    fun update(@Path("id") id: Int, @Body pessoa: UserModel): Call<UserModel>

    @DELETE("/Pessoas/{id}")
    fun delete(@Path("id") id: Int): Call<UserModel>

}