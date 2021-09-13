package com.leavingston.mapexample.repository

import com.leavingston.mapexample.models.ModelClass
import com.leavingston.mapexample.networking.GenericApiRequest
import com.leavingston.mapexample.networking.RemoteApiService

class downloadDataRepo(private val api : RemoteApiService) : GenericApiRequest() {

    suspend fun getData() = apiRequest {
        api.getData()
    }

}