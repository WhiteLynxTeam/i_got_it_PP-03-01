package site.pnpl.igotit.data.dbo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.dao.CursesDao
import site.pnpl.igotit.data.dbo.dao.LessonsDao
import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.data.dbo.entity.CoursesScheduleEntity
import site.pnpl.igotit.data.dbo.entity.LessonEntity
import site.pnpl.igotit.data.dbo.entity.MyCoursesEntity

@Database(
    entities = [
        ClubEntity::class,
        CoursesScheduleEntity::class,
        MyCoursesEntity::class,
        LessonEntity::class,
//        TagEntity::class,
//        CrossTagClubEntity::class,
//        CrossTagCoursEntity::class
    ],
    version = 8,
    exportSchema = false
)
abstract class IgotitDatabase : RoomDatabase() {
    abstract fun clubsDao(): ClubsDao
    abstract fun cursesDao(): CursesDao
    abstract fun tagsDao(): TagsDao
    abstract fun lessonsDao(): LessonsDao
}