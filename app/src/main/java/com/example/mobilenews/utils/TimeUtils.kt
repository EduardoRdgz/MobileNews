package com.example.mobilenews.utils

import java.text.SimpleDateFormat
import java.util.Locale
import kotlin.text.format

object TimeUtils {

    fun formatTime(timeString: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        val date = inputFormat.parse(timeString)
        val outputFormat = SimpleDateFormat("HH:mm ,  MMM dd, yyyy", Locale.getDefault())
        return outputFormat.format(date)
    }
}


