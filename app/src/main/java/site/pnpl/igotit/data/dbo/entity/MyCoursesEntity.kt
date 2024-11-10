package site.pnpl.igotit.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "MYCOURSES")
data class MyCoursesEntity(
    @field: ColumnInfo(name = "uuid") @field:PrimaryKey val uuid: UUID,
)