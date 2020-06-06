package com.example.myapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ToDoModel (
    val description:String,
    val checked:String,
    val id:String

):Parcelable