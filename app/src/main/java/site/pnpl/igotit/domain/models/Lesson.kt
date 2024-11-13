package site.pnpl.igotit.domain.models

import java.util.UUID

data class Lesson(
    val id: Long? = -1L,
    var uuid: UUID? = null,
    val uuidSchedule: UUID,
    val dateMilis: Long,
    var title: String = "",
)