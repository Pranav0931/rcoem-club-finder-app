package com.hdaf.clubfinder

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Club(
    val id: Int,
    val name: String,
    val fullName: String,
    val department: String,
    val interest: String,
    val logo: String, // Using emoji for now, can be replaced with drawable IDs
    val description: String,
    val leaders: String,
    val contact: String,
    val keywords: String
) : Parcelable
