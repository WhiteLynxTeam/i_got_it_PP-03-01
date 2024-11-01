package site.pnpl.igotit.data.dbo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.dao.CursesDao
import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.data.dbo.entity.CrossTagClubEntity
import site.pnpl.igotit.data.dbo.entity.CrossTagCoursEntity
import site.pnpl.igotit.data.dbo.entity.CoursEntity
import site.pnpl.igotit.data.dbo.entity.TagEntity

@Database(
    entities = [
        ClubEntity::class,
        CoursEntity::class,
        TagEntity::class,
        CrossTagClubEntity::class,
        CrossTagCoursEntity::class
    ],
    version = 3,
    exportSchema = false
)
abstract class IgotitDatabase : RoomDatabase() {
    abstract fun clubsDao(): ClubsDao
    abstract fun cursesDao(): CursesDao
    abstract fun tagsDao(): TagsDao
}