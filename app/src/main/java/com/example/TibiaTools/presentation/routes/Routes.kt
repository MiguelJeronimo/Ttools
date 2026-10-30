package com.example.TibiaTools.presentation.routes

import android.os.Parcelable
import androidx.navigation3.runtime.NavKey
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

sealed class HomeRoutes: NavKey, Parcelable {
    @Serializable
    @Parcelize
    object Home: HomeRoutes()
    @Serializable
    @Parcelize
    object Tools: HomeRoutes()
}