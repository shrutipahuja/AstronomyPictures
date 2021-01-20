package com.example.astronomypictures.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Model class for each image and its attributes
 */
@Parcelize
data class Image(
    val copyright: String?,
    val date: String,
    val explanation: String,
    val hdurl: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
) : Parcelable