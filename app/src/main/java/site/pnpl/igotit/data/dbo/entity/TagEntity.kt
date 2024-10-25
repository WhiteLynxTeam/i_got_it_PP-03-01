package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TAGS")
data class TagEntity(
    @field: ColumnInfo(name = "uuid") @field:PrimaryKey val uuid: String,
    @field: ColumnInfo(name = "name") val name: String,
)
