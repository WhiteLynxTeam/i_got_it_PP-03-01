package site.pnpl.igotit.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import site.pnpl.igotit.data.dbo.entity.LessonEntity
import java.util.UUID

@Dao
interface LessonsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(lessonEntity: LessonEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listLessonEntity: List<LessonEntity>): List<Long>

    @Query("DELETE FROM LESSON  WHERE uuid IN (:listMyCourses)")
    fun delLessons(listMyCourses: List<UUID>): Int

    @Query("SELECT * FROM LESSON WHERE dateMilis >= :nowMilis ORDER BY dateMilis ASC")
    fun getFirstNextLesson(nowMilis: Long): List<LessonEntity>

    @Query("SELECT * FROM LESSON WHERE uuidSchedule IN (:listUuid) ORDER BY dateMilis ASC")
    fun getLessonsBySchedules(listUuid: List<UUID>): List<LessonEntity>

    @Query("SELECT * FROM LESSON ORDER BY dateMilis ASC")
    fun getAllLessons(): List<LessonEntity>

    @Query("DELETE FROM LESSON")
    fun trunc(): Int


}