package site.pnpl.igotit.domain.models

enum class WeekCalendar(
    val RuShort: String,
    val RuLong: String,
    val pos: Int,
    var isAvailable: Boolean =false,
    var isNow: Boolean = false
) {
    MONDAY("пн","понедельник", 1),
    TUESDAY("вт","вторник", 2),
    WEDNESDAY("ср","среда", 3),
    THURSDAY("чт","четверг", 4),
    FRIDAY("пт","пятница", 5),
    SATURDAY("сб","суббота", 6),
    SUNDAY("вс","воскресенье", 7),
}