package site.pnpl.igotit.domain.models

data class Clubs(
    val id: Int,
    val title: String,
    val level: String,
    val numberClasses: String,
    val perWeek: String,
    val duration: String,
    val totalQuantity: String,
    val description: String,
    val about: String = "",
)