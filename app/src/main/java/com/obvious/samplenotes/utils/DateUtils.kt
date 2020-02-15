package com.obvious.samplenotes.utils

import android.text.format.DateFormat
import java.util.*

/**
 * Created by Prasoon on 2020-02-13.
 */
class DateUtils {
    companion object {
        fun getDate(time: Long): String {
            val cal = Calendar.getInstance(Locale.ENGLISH)
            cal.timeInMillis = time
            return DateFormat.format("dd MMM yyyy", cal).toString()
        }
    }
}