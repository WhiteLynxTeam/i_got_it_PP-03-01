package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "COURSES")
data class CoursEntity(
    @field: ColumnInfo(name = "uuid") @field:PrimaryKey val uuid: String,
    @field: ColumnInfo(name = "title") val title: String,
    @field: ColumnInfo(name = "level") val level: String,
    @field: ColumnInfo(name = "description") val description: String,
    @field: ColumnInfo(name = "length") val length: String,
    @field: ColumnInfo(name = "frequency") val frequency: String,
    @field: ColumnInfo(name = "isFavorites") var isFavorites: Boolean = false,
)