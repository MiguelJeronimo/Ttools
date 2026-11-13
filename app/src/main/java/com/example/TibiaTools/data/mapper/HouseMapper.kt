package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.HouseDetailDto
import com.example.TibiaTools.data.dto.HouseInformationResponseDto
import com.example.TibiaTools.data.dto.HouseListItemDto
import com.example.TibiaTools.data.dto.HouseStatusDto
import com.example.TibiaTools.data.dto.HousesDataDto
import com.example.TibiaTools.data.dto.HousesResponseDto
import com.example.TibiaTools.domain.model.HouseDetail
import com.example.TibiaTools.domain.model.HouseInformationResponse
import com.example.TibiaTools.domain.model.HouseListItem
import com.example.TibiaTools.domain.model.HouseStatus
import com.example.TibiaTools.domain.model.HousesData
import com.example.TibiaTools.domain.model.HousesResponse

fun HousesResponseDto.toDomain(): HousesResponse = HousesResponse(
    houses = houses?.toDomain()
)

fun HouseInformationResponseDto.toDomain(): HouseInformationResponse = HouseInformationResponse(
    house = house?.toDomain()
)

fun HousesDataDto.toDomain(): HousesData = HousesData(
    world = world.orEmpty(),
    town = town.orEmpty(),
    houseList = houseList.map { it.toDomain() },
    guildhallList = guildhallList?.map { it.toDomain() } ?: emptyList()
)

fun HouseDetailDto.toDomain(): HouseDetail = HouseDetail(
    world = world.orEmpty(),
    town = town.orEmpty(),
    img = img.orEmpty(),
    name = name.orEmpty(),
    type = type.orEmpty(),
    beds = beds,
    size = size,
    rent = rent,
    status = status?.toDomain()
)

fun HouseListItemDto.toDomain(): HouseListItem = HouseListItem(
    name = name.orEmpty(),
    houseId = houseId,
    size = size,
    rent = rent,
    isRented = rented
)

fun HouseStatusDto.toDomain(): HouseStatus = HouseStatus(
    isAuctioned = isAuctioned ?: false,
    isRented = isRented ?: false,
    isMoving = isMoving ?: false,
    isTransfering = isTransfering ?: false,
    original = original.orEmpty()
)
