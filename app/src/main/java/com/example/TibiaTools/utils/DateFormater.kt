package com.example.TibiaTools.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.time.format.FormatStyle
import java.util.Locale

object DateFormater {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatIsoDateTime(
        raw: String?,
        zone: ZoneId = ZoneId.systemDefault(),
        locale: Locale = Locale.getDefault(),
    ): String? {
        if (raw.isNullOrBlank()) return null
        return try {
            Instant.parse(raw)
                .atZone(zone)
                .format(
                    DateTimeFormatter
                        .ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.SHORT)
                        .withLocale(locale)
                )
        } catch (e: DateTimeParseException) {
            null
        }
    }
}