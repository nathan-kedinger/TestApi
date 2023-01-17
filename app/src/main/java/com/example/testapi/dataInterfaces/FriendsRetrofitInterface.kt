package com.example.testapi.dataInterfaces

import com.example.testapi.models.FriendsModel
import retrofit2.Response
import retrofit2.http.*

interface FriendsRetrofitInterface {
    @POST("friends/create.php")
    suspend fun postFriends(@Body data: FriendsModel): Response<FriendsModel>

    @GET("friends/read.php")
    suspend fun getFriends(): Response<List<FriendsModel>>

    @PUT("friends/update")
    suspend fun updateFriends(@Path("uuid") uuid: String, @Body user: FriendsModel): Response<FriendsModel>

    @DELETE("friends/delete")
    suspend fun deleteFriends(@Path("uuid") uuid: String): Response<Unit>
}