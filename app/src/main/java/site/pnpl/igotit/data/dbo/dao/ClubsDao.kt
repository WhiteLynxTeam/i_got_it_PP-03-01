package site.pnpl.igotit.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.data.dbo.entity.CoursesScheduleEntity
import site.pnpl.igotit.data.dbo.entity.MyCoursesEntity
import java.util.UUID

@Dao
interface ClubsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMyCourse(myCoursesEntity: MyCoursesEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllClubs(clubEntity: List<ClubEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllCorsesScheduler(clubEntity: List<CoursesScheduleEntity>): List<Long>

    @Query("SELECT * FROM CLUBS")
    fun getClubs(): List<ClubEntity>

    @Query("SELECT * FROM CLUBS  WHERE type = :type")
    fun getEntities(type: String): List<ClubEntity>

    @Query("SELECT * FROM CLUBS WHERE uuid IN (:listMyCourses)")
    fun getEntities(listMyCourses: List<UUID>): List<ClubEntity>

    @Query("SELECT uuidCourses FROM COURSES_SCHEDULE WHERE uuid IN (:listMyCourses) GROUP BY uuidCourses")
    fun getUuidCoursesByUuidScheduler(listMyCourses: List<UUID>): List<UUID>

    @Query("SELECT * FROM COURSES_SCHEDULE  WHERE uuidCourses = :uuidCourse")
    fun getCoursesSchedulerByUuidCourse(uuidCourse: UUID): List<CoursesScheduleEntity>

    @Query("SELECT * FROM COURSES_SCHEDULE  WHERE uuid = :uuid")
    fun getCoursesSchedulerByUuid(uuid: UUID): CoursesScheduleEntity

    @Query("SELECT * FROM MYCOURSES")
    fun getMyCourses(): List<MyCoursesEntity>

    @Query("SELECT * FROM CLUBS  WHERE isFavorites = 1 OR isFavorites = 1")
    fun getMyCoursesByFlag(): List<ClubEntity>

    @Query("SELECT * FROM CLUBS  WHERE type = :type AND id = :id")
    fun getEntity(type: String, id: Int): ClubEntity

    @Query("SELECT * FROM CLUBS  WHERE uuid = :uuid")
    fun getEntity(uuid: UUID): ClubEntity

    @Query("SELECT isFavorites FROM CLUBS  WHERE id = :id")
    fun getFavoriteById(id: Int): Boolean


    @Query("UPDATE CLUBS SET isMyCourse = 1 WHERE id = :id")
    fun setMyCourse(id: Int): Int

    //    @Query("UPDATE CLUBS SET isFavorites = true WHERE id = :id")
    @Query("UPDATE CLUBS SET isFavorites = CASE WHEN isFavorites THEN 0 ELSE 1 END WHERE id = :id")
    fun setFavorites(id: Int): Int

    @Query("DELETE FROM CLUBS")
    fun truncClubs(): Int

    @Query("DELETE FROM MYCOURSES")
    fun truncMyCourses(): Int

    @Query("DELETE FROM MYCOURSES  WHERE uuid IN (:listMyCourses)")
    fun deleteMyCourse(listMyCourses: List<UUID>): Int

    @Query("DELETE FROM COURSES_SCHEDULE")
    fun truncScheduler(): Int

//
//    @Query("SELECT isMyCourse FROM CLUBS  WHERE id = :id")
//    fun getMyCourseById(id: Int): Boolean

}