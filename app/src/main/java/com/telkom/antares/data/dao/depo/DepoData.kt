package com.telkom.antares.data.dao.depo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class DepoData(
    @PrimaryKey(autoGenerate = true)
    val id_depo : Int,
    val name : String,
    val major : String,
    val minor : String,
    val latitude : String,
    val longitude : String,
    val location : String,
    val lastdata : String,
) : Parcelable

