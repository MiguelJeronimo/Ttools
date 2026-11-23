package com.example.TibiaTools.domain.model

data class HousesResponse(
    val houses: HousesData? = null
)

data class HouseInformationResponse(
    val house: HouseDetail? = null
)

data class HousesData(
    val world: String = "",
    val town: String = "",
    val houseList: List<HouseListItem> = emptyList(),
    val guildhallList: List<HouseListItem> = emptyList()
)

data class HouseDetail(
    val world: String = "",
    val town: String = "",
    val img: String = "",
    val name: String = "",
    val type: String = "",
    val beds: Int = 0,
    val size: Int = 0,
    val rent: Int = 0,
    val status: HouseStatus? = null
)

data class HouseListItem(
    val name: String = "",
    val houseId: Int = 0,
    val size: Int = 0,
    val rent: Int = 0,
    val isRented: Boolean = false
)

data class HouseStatus(
    val isAuctioned: Boolean = false,
    val isRented: Boolean = false,
    val isMoving: Boolean = false,
    val isTransfering: Boolean = false,
    val original: String = ""
)

data class HouseRental(
    val owner: String = "",
    val ownerSex: String = "",
    val paidUntil: String = "",
    val movingDate: String = "",
    val transferReceiver: String = "",
    val transferPrice: Int = 0,
    val isTransferAccept: Boolean = false
)
