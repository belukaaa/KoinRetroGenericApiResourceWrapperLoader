package com.leavingston.mapexample.networking

import com.leavingston.mapexample.exceptions.ApiExceptions
import com.leavingston.mapexample.models.Error.Companion.ERROR_BLOCKED_USER
import com.leavingston.mapexample.models.Error.Companion.REFRESH_TOKEN
import com.leavingston.mapexample.models.Error.Companion.UNKNOWN_ERROR
import com.leavingston.mapexample.models.Error.Companion.USER_ALREADY_REGISTERED
import com.leavingston.mapexample.models.Error.Companion.USER_DOES_NOT_EXIST
import retrofit2.Response

abstract class GenericApiRequest {
    suspend fun <T: Any> apiRequest(call: suspend () -> Response<T>): T{
        val response = call.invoke()
        var error = ""
        if (response.isSuccessful){
            return response.body()!!
        }else{
            error = when {
                response.code() == 401 -> {
                    REFRESH_TOKEN
                }
                response.code() == 500 -> {
                    REFRESH_TOKEN
                }
                response.code() == 400 -> {
                    ERROR_BLOCKED_USER
                }
                response.code() == 422 -> {
                    USER_ALREADY_REGISTERED
                }
                response.code() == 404 -> {
                    USER_DOES_NOT_EXIST
                }
                else -> {
                    UNKNOWN_ERROR
                }
            }
        }
        throw ApiExceptions(error)
    }
}