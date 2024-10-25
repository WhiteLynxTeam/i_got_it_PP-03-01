package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "CROSS_TAGS_CURSES", primaryKeys = ["tagUuid", "coursUuid"])
data class CrossTagCoursEntity(
    @field: ColumnInfo(name = "tagUuid") val tagUuid: String,
    @field: ColumnInfo(name = "coursUuid") val coursUuid: String,
)
