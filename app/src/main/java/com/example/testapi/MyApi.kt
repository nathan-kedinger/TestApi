package com.example.testapi

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

val retrofit = Retrofit.Builder()
    .baseUrl("https://bts-sio-kedinger.fr/vooov-api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
val myApi = retrofit.create(MyApi::class.java)

data class DataModel(val uuid: String, val user1: String, val user2: String)

interface MyApi {
    @GET("user-agent")
    fun getUserAgent() : Call<String> //retrofit se charge de créer un corps à cette requête à partir du client créé dans mainactivity

    @POST("friends/create.php")
    suspend fun postData(@Body data: DataModel): Response<DataModel>

}

