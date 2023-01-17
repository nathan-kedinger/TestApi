package com.example.testapi.dataInterfaces

import com.example.testapi.models.RecordsModel
import retrofit2.Response
import retrofit2.http.*

interface RecordsRetrofitInterface {
    @POST("records/create.php")
    suspend fun postRecords(@Body data: RecordsModel): Response<RecordsModel>

    @GET("records/read.php")
    suspend fun getRecords(): Response<MutableList<RecordsModel>>

    @GET("records/readOne.php")
    suspend fun getOneRecord(@Query("uuid") uuid: String): Response<RecordsModel>

    @PUT("records/update.php")
    suspend fun updateRecords(@Body record: RecordsModel): Response<RecordsModel>

    @DELETE("records/delete.php")
    suspend fun deleteRecords(@Query("uuid") uuid: String): Response<RecordsModel >
}