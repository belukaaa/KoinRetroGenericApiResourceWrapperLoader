package com.leavingston.mapexample.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelClass(
    @SerializedName("albumId") var albumId : Int,
    @SerializedName("id") var id : Int,
    @SerializedName("title") var title : String,
    @SerializedName("url") var url : String,
    @SerializedName("thumbnailUrl") var thumbnailUrl : String
) : Parcelable