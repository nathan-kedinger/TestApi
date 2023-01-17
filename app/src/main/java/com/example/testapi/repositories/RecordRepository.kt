package com.example.testapi.repositories

import android.content.ContentValues
import android.util.Log
import com.example.testapi.dataInterfaces.RecordsRetrofitInterface
import com.example.testapi.models.RecordsModel
import kotlinx.coroutines.*
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.io.IOException

class RecordRepository {

    val retrofit = Retrofit.Builder()// Construction du client Retrofit
        .baseUrl("https://bts-sio-kedinger.fr/vooov-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val create: RecordsRetrofitInterface = retrofit.create(RecordsRetrofitInterface::class.java)

    suspend fun createRecordData(record: RecordsModel): Response<RecordsModel>{
        return try{
            create.postRecords(record)
        } catch (e: Exception) {
            throw IOException("Error creating record",e)
        }
    }

    private val read: RecordsRetrofitInterface = retrofit.create(RecordsRetrofitInterface::class.java)

    suspend fun readRecordData(): Response<MutableList<RecordsModel>>{
        return try {
            read.getRecords()
        } catch(e: Exception){
            throw IOException("Error fetching record", e)
        }
    }

    private val readOne: RecordsRetrofitInterface = retrofit.create(RecordsRetrofitInterface::class.java)

    suspend fun readOneRecordData(recordUuid: String): Response<RecordsModel> {
        return try {
            readOne.getOneRecord(recordUuid)
        } catch (e: Exception) {
            throw IOException("Error fetching record", e)
        }
    }

    private val update: RecordsRetrofitInterface = retrofit.create(RecordsRetrofitInterface::class.java)

    suspend fun updateRecordData(record: RecordsModel): Response<RecordsModel>{
        return try {
            update.updateRecords(record)
        } catch (e: Exception) {
            throw IOException("Error updating record", e)
        }
    }

    private val delete: RecordsRetrofitInterface = retrofit.create(RecordsRetrofitInterface::class.java)

    suspend fun deleteRecordData(recordUuid: String): Response<RecordsModel>{
        return try {
            delete.deleteRecords(recordUuid)
        } catch (e: Exception) {
            throw IOException("Error updating record", e)
        }
    }

}