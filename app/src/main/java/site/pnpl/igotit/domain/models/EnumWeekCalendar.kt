package site.pnpl.igotit.domain.models

import site.pnpl.igotit.R

enum class EnumWeekCalendar(
    val RuShort: String,
    val RuLong: String,
    val pos: Int,
    val textViewId: Int,
    var isAvailable: Boolean =false,
    var isNow: Boolean = false,
) {
    MONDAY("пн","понедельник", 1, R.id.day_1),
    TUESDAY("вт","вторник", 2, R.id.day_2),
    WEDNESDAY("ср","среда", 3, R.id.day_3),
    THURSDAY("чт","четверг", 4, R.id.day_4),
    FRIDAY("пт","пятница", 5, R.id.day_5),
    SATURDAY("сб","суббота", 6, R.id.day_6),
    SUNDAY("вс","воскресенье", 7, R.id.day_7);

    companion object {
        fun getTextViewIdByPos(position: Int): Int? {
            return entries.find { it.pos == position }?.textViewId
        }
        fun getRuShortByTextViewId(id: Int): String? {
            return entries.find { it.textViewId == id }?.RuShort
        }
    }
}