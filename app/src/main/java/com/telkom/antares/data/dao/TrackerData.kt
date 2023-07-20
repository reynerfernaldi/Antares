package com.telkom.antares.data.dao

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class TrackerData(
    @PrimaryKey(autoGenerate = true)
    val id_tracker : Int,
    val name : String,
    val major : String,
    val minor : String,
    val latitude : String,
    val longitude : String,
    val location : String,
    val lastdata : String,
) : Parcelable

