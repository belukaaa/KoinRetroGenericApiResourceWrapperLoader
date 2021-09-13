package com.leavingston.mapexample.models

class Error {
    companion object{
        const val USER_ALREADY_REGISTERED = "422"
        const val REFRESH_TOKEN = "401"
        const val ERROR_BLOCKED_USER = "400"
        const val USER_DOES_NOT_EXIST = "404"
        const val UNKNOWN_ERROR = "410"
    }
}