package site.pnpl.igotit.domain.models

import androidx.room.ColumnInfo
import java.util.UUID

data class CoursesSchedule(
    val id: Int,
    var uuid: UUID,
    val idCourses: Int,
    val uuidCourses: UUID,
    val year: String,
    val month: String,
    val day: String = "",
    val dayOfWeek: String = "",
    val startHour: String,
    val startMinute: String,
    val endHour: String,
    val endMinute: String,
    val type: String,
    val shortType: String = "",
)
