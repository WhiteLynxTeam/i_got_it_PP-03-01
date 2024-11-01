package site.pnpl.igotit.domain.models

import java.util.UUID

data class Clubs(
    val id: Int,
    override var uuid: UUID? = null,
    val title: String,
    val level: String,
    val numberClasses: String,
    val perWeek: String,
    val duration: String,
    val totalQuantity: String,
    val description: String,
    val about: String = "",
    val isFavorite: Boolean,
    val isMyCourse: Boolean,
)  : HasUuid