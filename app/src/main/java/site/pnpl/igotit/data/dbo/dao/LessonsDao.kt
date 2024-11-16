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

//    @Query("DELETE FROM COMMENTS WHERE commentId = :id")
//    fun del(id: Int)
//
//    @Query("SELECT * FROM COMMENTS WHERE imageId = :id")
//    fun getComments(id: Int): List<CommentEntity>

//    @Query("SELECT * FROM IMAGES  WHERE id = :id")
//    fun getImageById(id: Int): ImageEntity

    @Query("SELECT * FROM LESSON WHERE dateMilis >= :nowMilis ORDER BY dateMilis ASC")
    fun getFirstNextLesson(nowMilis: Long): List<LessonEntity>

    @Query("SELECT * FROM LESSON WHERE uuidSchedule IN (:listUuid) ORDER BY dateMilis ASC")
    fun getLessonsBySchedules(listUuid: List<UUID>): List<LessonEntity>

    @Query("DELETE FROM LESSON")
    fun trunc(): Int


}