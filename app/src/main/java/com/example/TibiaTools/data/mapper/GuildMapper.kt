package com.example.TibiaTools.data.mapper

import com.example.TibiaTools.data.dto.GuildActiveDto
import com.example.TibiaTools.data.dto.GuildDetailDto
import com.example.TibiaTools.data.dto.GuildHallDto
import com.example.TibiaTools.data.dto.GuildMemberDto
import com.example.TibiaTools.data.dto.GuildNameResponseDto
import com.example.TibiaTools.data.dto.GuildsDataDto
import com.example.TibiaTools.data.dto.GuildsResponseDto
import com.example.TibiaTools.domain.model.GuildActive
import com.example.TibiaTools.domain.model.GuildDetail
import com.example.TibiaTools.domain.model.GuildHall
import com.example.TibiaTools.domain.model.GuildMember
import com.example.TibiaTools.domain.model.GuildNameResponse
import com.example.TibiaTools.domain.model.GuildsData
import com.example.TibiaTools.domain.model.GuildsResponse

fun GuildsResponseDto.toDomain(): GuildsResponse = GuildsResponse(
    guilds = guilds?.toDomain()
)

fun GuildNameResponseDto.toDomain(): GuildNameResponse = GuildNameResponse(
    guild = guild?.toDomain()
)

fun GuildsDataDto.toDomain(): GuildsData = GuildsData(
    active = active?.map { it.toDomain() }
)

fun GuildActiveDto.toDomain(): GuildActive = GuildActive(
    name = name.orEmpty(),
    logoUrl = logoUrl.orEmpty(),
    description = description.orEmpty()
)

fun GuildDetailDto.toDomain(): GuildDetail = GuildDetail(
    name = name.orEmpty(),
    world = world.orEmpty(),
    logoUrl = logoUrl.orEmpty(),
    description = description.orEmpty(),
    active = active ?: false,
    inWar = inWar ?: false,
    playersOnline = playersOnline,
    membersTotal = membersTotal,
    founded = founded.orEmpty(),
    members = members?.map { it.toDomain() } ?: emptyList(),
    guildhalls = guildhalls?.map { it.toDomain() } ?: emptyList()
)

fun GuildHallDto.toDomain(): GuildHall = GuildHall(
    name = name.orEmpty(),
    world = world.orEmpty(),
    paidUntil = paidUntil.orEmpty()
)

fun GuildMemberDto.toDomain(): GuildMember = GuildMember(
    name = name.orEmpty(),
    title = title.orEmpty(),
    rank = rank.orEmpty(),
    vocation = vocation.orEmpty(),
    level = level.orEmpty(),
    joined = joined.orEmpty(),
    status = status.orEmpty()
)
