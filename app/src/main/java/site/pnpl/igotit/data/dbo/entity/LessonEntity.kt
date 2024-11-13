package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import site.pnpl.igotit.domain.models.HasUuid
import java.util.UUID

@Entity(tableName = "LESSON")
data class LessonEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) var id: Int = 0,
    @field: ColumnInfo(name = "uuid") override var uuid: UUID? = null,
    @field: ColumnInfo(name = "uuidSchedule") var uuidSchedule: UUID? = null,
    @field: ColumnInfo(name = "year") val year: String = "",
    @field: ColumnInfo(name = "month") val month: String = "",
    @field: ColumnInfo(name = "day") val day: String = "",
    @field: ColumnInfo(name = "dateMilis") val dateMilis: Long = 0L,
) : HasUuid
