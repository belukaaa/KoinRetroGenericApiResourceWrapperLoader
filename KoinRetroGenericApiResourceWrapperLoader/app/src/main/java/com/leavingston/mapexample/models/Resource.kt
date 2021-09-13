package com.leavingston.mapexample.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

class Resource<T> (
    val status: Status? = null,
    val data: T? = null,
    val message: String? = null
): Serializable {

    enum class Status {
        SUCCESS, ERROR, NO_INTERNET, ERROR_401, BLOCKED_USER, USER_DOES_NOT_EXIST
    }
}