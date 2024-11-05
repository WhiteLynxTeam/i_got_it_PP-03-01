package site.pnpl.igotit.utils

import android.os.Build
import androidx.annotation.RequiresApi
import site.pnpl.igotit.domain.DATE_PATTERN_DAY_ONLY
import site.pnpl.igotit.domain.DATE_PATTERN_WEEK_DAY
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Date
import java.util.Locale


fun Long.toTextDateByFormat(dateFormat: String): String {
    val df = SimpleDateFormat(dateFormat, Locale.getDefault())
    return df.format(Date(this))
}

@RequiresApi(Build.VERSION_CODES.O)
fun  LocalDate.toStringByFormat(dateFormat: String, locale: Locale = Locale.getDefault()): String {
    val df = SimpleDateFormat(dateFormat, Locale.getDefault())
    val formatter = DateTimeFormatter.ofPattern(dateFormat, locale)
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toStringByFormat(dateFormat: String): String =
    this.format(DateTimeFormatter.ofPattern(dateFormat))

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toWeekDay(): String = this.toStringByFormat(DATE_PATTERN_WEEK_DAY)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toDayOnly(): String = this.toStringByFormat(DATE_PATTERN_DAY_ONLY)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toMonthAnYear(): String =
    this.month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault()) + " ${this.year}"
