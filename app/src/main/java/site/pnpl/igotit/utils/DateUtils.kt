package site.pnpl.igotit.utils

import android.os.Build
import androidx.annotation.RequiresApi
import site.pnpl.igotit.domain.DATE_PATTERN_DAY_ONLY
import site.pnpl.igotit.domain.DATE_PATTERN_WEEK_DAY
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


fun Long.toTextDateByFormat(dateFormat: String): String {
    val df = SimpleDateFormat(dateFormat, Locale.getDefault())
    return df.format(Date(this))
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toStringByFormat(dateFormat: String, locale: Locale = Locale.getDefault()): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat, locale)
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toStringByFormat(dateFormat: String): String =
    this.format(DateTimeFormatter.ofPattern(dateFormat))

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toRuStringByFormat(dateFormat: String): String {
    val formatter = DateTimeFormatter.ofPattern(dateFormat, Locale("ru", "RU"))
    return this.format(formatter)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toWeekDay(): String = this.toStringByFormat(DATE_PATTERN_WEEK_DAY)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toRuWeekDay(): String = this.toRuStringByFormat(DATE_PATTERN_WEEK_DAY)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toDayOnly(): String = this.toStringByFormat(DATE_PATTERN_DAY_ONLY)

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDate.toGetFirstDayOfWeek(): LocalDate {
    /*** dayOfWeek: понедельник - 1 ... воскресенье -7*/
    return this.minusDays(this.dayOfWeek.value.toLong() - 1)
}

/*val currentDate = LocalDate.now()
println(currentDate.dayOfWeek)
println(currentDate.dayOfWeek.value)
println(currentDate.toWeekDay())
println(currentDate.toRuWeekDay())
println(currentDate.dayOfMonth)
println(currentDate.toDayOnly())
println(currentDate.toStringByFormat(DATE_PATTERN_DEFAULT,Locale("ru", "RU")))
println(currentDate.toRuStringByFormat(DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH))
println(currentDate.toStringByFormat(DATE_PATTERN_FULL_DATE_AND_TEXT_MONTH,Locale("ru", "RU")))*/
