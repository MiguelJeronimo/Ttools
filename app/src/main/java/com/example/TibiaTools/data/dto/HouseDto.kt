package com.example.TibiaTools.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class HousesResponseDto(
    @SerializedName("houses")
    @SerialName("houses")
    val houses: HousesDataDto? = null
)

@Keep
@Serializable
data class HouseInformationResponseDto(
    @SerializedName("house")
    @SerialName("house")
    val house: HouseDetailDto? = null
)

@Keep
@Serializable
data class HousesDataDto(
    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("town")
    @SerialName("town")
    val town: String? = null,

    @SerializedName("house_list")
    @SerialName("house_list")
    val houseList: List<HouseListItemDto> = emptyList(),

    @SerializedName("guildhall_list")
    @SerialName("guildhall_list")
    val guildhallList: List<HouseListItemDto>? = null
)

@Keep
@Serializable
data class HouseDetailDto(
    @SerializedName("world")
    @SerialName("world")
    val world: String? = null,

    @SerializedName("town")
    @SerialName("town")
    val town: String? = null,

    @SerializedName("img")
    @SerialName("img")
    val img: String? = null,

    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("type")
    @SerialName("type")
    val type: String? = null,

    @SerializedName("beds")
    @SerialName("beds")
    val beds: Int = 0,

    @SerializedName("size")
    @SerialName("size")
    val size: Int = 0,

    @SerializedName("rent")
    @SerialName("rent")
    val rent: Int = 0,

    @SerializedName("status")
    @SerialName("status")
    val status: HouseStatusDto? = null
)

@Keep
@Serializable
data class HouseListItemDto(
    @SerializedName("name")
    @SerialName("name")
    val name: String? = null,

    @SerializedName("house_id")
    @SerialName("house_id")
    val houseId: Int = 0,

    @SerializedName("size")
    @SerialName("size")
    val size: Int = 0,

    @SerializedName("rent")
    @SerialName("rent")
    val rent: Int = 0,

    @SerializedName("rented")
    @SerialName("rented")
    val rented: Boolean = false
)

@Keep
@Serializable
data class HouseStatusDto(
    @SerializedName("is_auctioned")
    @SerialName("is_auctioned")
    val isAuctioned: Boolean? = null,

    @SerializedName("is_rented")
    @SerialName("is_rented")
    val isRented: Boolean? = null,

    @SerializedName("is_moving")
    @SerialName("is_moving")
    val isMoving: Boolean? = null,

    @SerializedName("is_transfering")
    @SerialName("is_transfering")
    val isTransfering: Boolean? = null,

    @SerializedName("original")
    @SerialName("original")
    val original: String? = null
)

@Keep
@Serializable
data class HouseRentalDto(
    @SerializedName("owner")
    @SerialName("owner")
    val owner: String? = null,

    @SerializedName("owner_sex")
    @SerialName("owner_sex")
    val ownerSex: String? = null,

    @SerializedName("paid_until")
    @SerialName("paid_until")
    val paidUntil: String? = null,

    @SerializedName("moving_date")
    @SerialName("moving_date")
    val movingDate: String? = null,

    @SerializedName("transfer_receiver")
    @SerialName("transfer_receiver")
    val transferReceiver: String? = null,

    @SerializedName("transfer_price")
    @SerialName("transfer_price")
    val transferPrice: Int = 0,

    @SerializedName("transfer_accept")
    @SerialName("transfer_accept")
    val transferAccept: Boolean? = null
)
