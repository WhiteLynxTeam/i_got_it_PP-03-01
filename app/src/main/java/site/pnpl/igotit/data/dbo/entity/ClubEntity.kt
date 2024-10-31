package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CLUBS")
data class ClubEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) var id: Int = 0,
    @field: ColumnInfo(name = "clubName") val clubName: String,
    @field: ColumnInfo(name = "type") val type: String,
    @field: ColumnInfo(name = "level") val level: String,
    @field: ColumnInfo(name = "description") val description: String,
    @field: ColumnInfo(name = "length") val length: String,
    @field: ColumnInfo(name = "frequency") val frequency: String,
    @field: ColumnInfo(name = "numberClasses") val numberClasses: String,
    @field: ColumnInfo(name = "totalQuantity") val totalQuantity: String,
    @field: ColumnInfo(name = "about") val about: String = "",
    @field: ColumnInfo(name = "isFavorites") var isFavorites: Boolean = false,
)