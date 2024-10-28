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
    @field: ColumnInfo(name = "isFavorites") var isFavorites: Boolean = false,
)

val listOfClubs = listOf(
    ClubEntity(
        type = "club",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятия",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней"
    ),
    ClubEntity(
        type = "club",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятия",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней"
    ),
    ClubEntity(
        type = "club",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятия",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней"
    ),
    ClubEntity(
        type = "course",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятия",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней"
    ),
    ClubEntity(
        type = "course",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятия",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней"
    ),
    ClubEntity(
        type = "course",
        clubName = "Хочу заговорить",
        level = "A1 - A2",
        numberClasses = "2 занятия",
        frequency = "2 раза в неделю",
        length = "1,5 часа",
        totalQuantity = "20 занятий",
        description = "Интенсивный разговорный английский для начальных уровней"
    ),
)