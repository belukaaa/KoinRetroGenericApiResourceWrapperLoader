package com.leavingston.mapexample.networking

import androidx.lifecycle.LiveData
import com.leavingston.mapexample.constants
import com.leavingston.mapexample.models.ModelClass
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface RemoteApiService {

    @GET("/photos")
    suspend fun getData () : Response<ArrayList<ModelClass>>

}