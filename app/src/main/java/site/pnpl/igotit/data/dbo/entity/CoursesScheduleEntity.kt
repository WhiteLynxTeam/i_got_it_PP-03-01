package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import site.pnpl.igotit.domain.models.HasUuid
import java.util.UUID

@Entity(tableName = "COURSES_SCHEDULE")
data class CoursesScheduleEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) var id: Int = 0,
    @field: ColumnInfo(name = "uuid") override var uuid: UUID? = null,
    @field: ColumnInfo(name = "idCourses") var idCourses: Int = -1,
    @field: ColumnInfo(name = "uuidCourses") var uuidCourses: UUID? = null,
    @field: ColumnInfo(name = "year") val year: String,
    @field: ColumnInfo(name = "month") val month: String,
    @field: ColumnInfo(name = "day") val day: String = "",
    @field: ColumnInfo(name = "dayOfWeek") val dayOfWeek: String = "",
    @field: ColumnInfo(name = "startHour") val startHour: String,
    @field: ColumnInfo(name = "startMinute") val startMinute: String,
    @field: ColumnInfo(name = "endHour") val endHour: String,
    @field: ColumnInfo(name = "endMinute") val endMinute: String,
    @field: ColumnInfo(name = "type") val type: String,
): HasUuid
