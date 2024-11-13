package site.pnpl.igotit.domain.irepository

import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.models.CoursesSchedule
import java.util.UUID

interface IClubRepository {
    suspend fun getEntities(type: String?): List<Clubs>
    suspend fun getEntities(listMyCourses: List<UUID>): List<Clubs>

    suspend fun getEntityById(type: String, id: Int): Clubs
    suspend fun getEntityByUuid(uuid: UUID): Clubs

    suspend fun getCoursesSchedulerByUuidCourseFromDb(uuid: UUID): List<CoursesSchedule>
    suspend fun getCourseScheduleByUuidFromDb(uuid: UUID): CoursesSchedule

    suspend fun getMyCourses(): List<UUID>

    suspend fun getFromApi(): Result<List<Clubs>>

    suspend fun saveSampleClubsToDb(): Boolean

    suspend fun setFavorites(id : Int): Result<Boolean>

    suspend fun setMyCourse(id: Int): Boolean
    suspend fun setMyCourse(uuid: UUID): Boolean

}