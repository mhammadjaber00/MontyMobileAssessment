package com.mohammadjaber.montymobileassessmentapplication.common

import android.icu.text.SimpleDateFormat
import java.util.*

object TimeAgo {
    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS
    private const val MONTH_MILLIS = 30L * DAY_MILLIS
    private const val YEAR_MILLIS = 365L * DAY_MILLIS

    fun getTimeAgo(timeString: String): String? {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        val date: Date = format.parse(timeString)
        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }
        val now = System.currentTimeMillis()
        if (time > now || time <= 0) {
            return null
        }
        val diff = now - time
        return when {
            diff < MINUTE_MILLIS -> {
                "just now"
            }
            diff < (2 * MINUTE_MILLIS) -> {
                "a minute ago"
            }
            diff < (50 * MINUTE_MILLIS) -> {
                (diff / MINUTE_MILLIS).toString() + " minutes ago"
            }
            diff < (90 * MINUTE_MILLIS) -> {
                "an hour ago"
            }
            diff < (24 * HOUR_MILLIS) -> {
                (diff / HOUR_MILLIS).toString() + " hours ago"
            }
            diff < (48 * HOUR_MILLIS) -> {
                "yesterday"
            }
            diff < (30L * DAY_MILLIS) -> {
                (diff / DAY_MILLIS).toString() + " days ago"
            }
            diff < (365L * DAY_MILLIS) -> {
                (diff / MONTH_MILLIS).toString() + " months ago"
            }
            else -> {
                (diff / YEAR_MILLIS).toString() + " years ago"
            }
        }
    }
}