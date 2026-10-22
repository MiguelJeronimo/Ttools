package com.example.TibiaTools.data.model

import androidx.annotation.Keep

@Keep
data class ApiHouses(
    var houses: Houses? = null
)

@Keep
data class ApiHousesInformation(
    var house: House? = null
)

@Keep
data class Houses(
    var world: String? = null,
    var town: String? = null,
    var house_list: ArrayList<HouseList> = ArrayList(),
    var guildhall_list: ArrayList<GuildhallList>? = null
)

@Keep
data class House(
    var world: String? = null,
    var town: String? = null,
    var img: String? = null,
    var name: String? = null,
    var type: String? = null,
    var beds: Int = 0,
    var size: Int = 0,
    var rent: Int = 0,
    var status: HouseStatus? = null
)

@Keep
open class HouseList(
    var name: String? = null,
    var house_id: Int = 0,
    var size: Int = 0,
    var rent: Int = 0,
    var rented: Boolean = false
) {
    fun isRented(): Boolean = rented
}

@Keep
class GuildhallList(
    name: String? = null,
    house_id: Int = 0,
    size: Int = 0,
    rent: Int = 0,
    rented: Boolean = false
) : HouseList(name, house_id, size, rent, rented)

@Keep
data class HouseStatus(
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

@Keep
data class HouseRental(
    var owner: String? = null,
    var owner_sex: String? = null,
    var paid_until: String? = null,
    var moving_date: String? = null,
    var transfer_receiver: String? = null,
    var transfer_price: Int = 0,
    var transfer_accept: Boolean? = null
)
