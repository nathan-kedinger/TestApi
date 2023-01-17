package com.example.testapi.dataInterfaces

import com.example.testapi.models.UserModel
import retrofit2.Response
import retrofit2.http.*

interface UsersRetrofitInterface {
    @POST("users/create.php")
    suspend fun postData(@Body data: UserModel): Response<UserModel>

    @GET("users/read.php")
    suspend fun getUsers(): Response<List<UserModel>>

    @PUT("users/update")
    suspend fun updateUser(@Path("uuid") uuid: String, @Body user: UserModel): Response<UserModel>

    @DELETE("users/delete")
    suspend fun deleteUser(@Path("uuid") uuid: String): Response<Unit>
}