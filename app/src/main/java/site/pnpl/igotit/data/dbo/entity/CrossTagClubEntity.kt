package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CROSS_TAGS_CLUBS")
data class CrossTagClubEntity(
    @field: ColumnInfo(name = "tagUuid") val tagUuid: String,
    @field: ColumnInfo(name = "clubUuid") val clubUuid: String,
)
