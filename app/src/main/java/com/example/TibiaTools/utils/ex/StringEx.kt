package com.example.TibiaTools.utils.ex

fun String?.toSpaceReplace(): String {
    val replace = this?.replace(" ", "") ?: ""
    return replace.lowercase()
}

//fun String.Type