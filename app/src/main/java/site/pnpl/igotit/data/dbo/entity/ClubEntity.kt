package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "CLUBS")
data class ClubEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) var id: Int = 0,
    @field: ColumnInfo(name = "uuid") override var uuid: UUID? = null,
    @field: ColumnInfo(name = "clubName") override val clubName: String,
    @field: ColumnInfo(name = "type") override val type: String,
    @field: ColumnInfo(name = "level") override val level: String,
    @field: ColumnInfo(name = "description") override val description: String,
    @field: ColumnInfo(name = "length") override val length: String,
    @field: ColumnInfo(name = "frequency") override val frequency: String,
    @field: ColumnInfo(name = "numberClasses") override val numberClasses: String,
    @field: ColumnInfo(name = "totalQuantity") override val totalQuantity: String,
    @field: ColumnInfo(name = "about") override val about: String = "",
    @field: ColumnInfo(name = "isFavorites") var isFavorites: Boolean = false,
    @field: ColumnInfo(name = "isMyCourse") var isMyCourse: Boolean = false,

    ) : BaseClub(
    clubName = clubName,
    type = type,
    level = level,
    description = description,
    length = length,
    frequency = frequency,
    numberClasses = numberClasses,
    totalQuantity = totalQuantity,
)