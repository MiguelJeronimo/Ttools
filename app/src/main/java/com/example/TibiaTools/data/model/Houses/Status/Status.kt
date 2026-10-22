package com.example.TibiaTools.data.model.Houses.Status

import androidx.annotation.Keep

@Keep
data class Status(
    var is_auctioned: Boolean? = null,
    var is_rented: Boolean? = null,
    var is_moving: Boolean? = null,
    var is_transfering: Boolean? = null,
    var original: String? = null
) {
    fun isAuctioned(): Boolean? = is_auctioned
    fun isRented(): Boolean? = is_rented
    fun isMoving(): Boolean? = is_moving
    fun isTransfering(): Boolean? = is_transfering
}
