package site.pnpl.igotit.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.dao.LessonsDao
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.data.dbo.entity.ClubsSample
import site.pnpl.igotit.data.dbo.entity.CoursesScheduleEntity
import site.pnpl.igotit.data.dbo.entity.MyCoursesEntity
import site.pnpl.igotit.data.dto.club.response.ClubDtoOut
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.models.CoursesSchedule
import site.pnpl.igotit.domain.sampleListOfClubs
import site.pnpl.igotit.utils.randomUuid
import java.util.UUID

class ClubRepository(
    private val clubApi: ClubApi,
    private val clubsDao: ClubsDao,
    private val lessonsDao: LessonsDao
) : IClubRepository {

    override suspend fun getCourseScheduleByUuidFromDb(uuid: UUID): CoursesSchedule {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getCoursesSchedulerByUuid(uuid)
        }
        return mapperCourseScheduleEntityToCourseSchedule(result)
    }

    override suspend fun getCoursesSchedulerByUuidCourseFromDb(uuidCourse: UUID): List<CoursesSchedule> {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getCoursesSchedulerByUuidCourse(uuidCourse)
        }
        return mapperCoursesScheduleEntityToCoursesSchedule(result)
    }

    override suspend fun getEntityByUuid(uuid: UUID): Clubs  {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getEntity(uuid)
        }
        return mapperClubEntityToClub(result)
    }

    override suspend fun getEntityById(type: String, id: Int): Clubs {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getEntity(type, id)
        }
        return mapperClubEntityToClub(result)
    }

    override suspend fun getEntities(type: String?): List<Clubs> {
        val result = withContext(Dispatchers.IO) {
            if (type.isNullOrEmpty()) {
                clubsDao.getMyCoursesByFlag()
            } else {
                clubsDao.getEntities(type)
            }
        }
        return mapperClubsEntityToClubs(result)
    }

    override suspend fun getEntities(listUuidMyCourses: List<UUID>): List<Clubs> {
        val result = withContext(Dispatchers.IO) {

            val listUuid = clubsDao.getUuidCoursesByUuidScheduler(listUuidMyCourses)
//            if (listMyCourses.isNotEmpty()) {
            clubsDao.getEntities(listUuid)
//            }
        }
        val listClub = mapperClubsEntityToClubs(result).map {
            it.apply { isMyCourse = true }
        }
        return listClub
    }

    override suspend fun getMyCourses(): List<UUID> {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getMyCourses()
        }

        println("ClubRepository - listMyCourses = $result")

        return mapperMyCoursesEntityToListUUID(result)
    }

    override suspend fun saveSampleClubsToDb(): Boolean {
        val result = withContext(Dispatchers.IO) {
            clubsDao.truncClubs()
            clubsDao.truncScheduler()
            clubsDao.truncMyCourses()
            /***В будущем убрать в свое место, а сейчас пусть будет все вместе*/
            lessonsDao.trunc()
            /****/

            sampleListOfClubs.randomUuid()

            val sampleListOfScheduleEntity: MutableList<CoursesScheduleEntity> = mutableListOf()
            sampleListOfClubs.forEach { parent ->
                parent.listSchedule.forEach { child ->
                    sampleListOfScheduleEntity.add(child.copy(uuidCourses = parent.uuid))
                }
            }
            sampleListOfScheduleEntity.randomUuid()

            clubsDao.insertAllClubs(mapperClubsSampleToClubsEntity(sampleListOfClubs))
            clubsDao.insertAllCorsesScheduler(sampleListOfScheduleEntity)
        }
        return result.isNotEmpty()
    }

    override suspend fun getFromApi(): Result<List<Clubs>> {
        val result = clubApi.getClubs()
        val outResult = result.map { mapperClubDtoOutToClubs(it) }
        return outResult
    }

    override suspend fun setFavorites(id: Int): Result<Boolean> {
        val result = withContext(Dispatchers.IO) {
            try {
                val countRow = clubsDao.setFavorites(id)
                if (countRow == 1) {
                    Result.success(clubsDao.getFavoriteById(id))
                } else {
                    Result.failure(Exception("No rows updated"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
        return result
    }

    /***Здесь возвращаем успешность установки флага моего курса, поэтому возвращаем просто Boolean*/
    override suspend fun setMyCourse(uuid: Int): Boolean {
        return withContext(Dispatchers.IO) {
            val countRow = clubsDao.setMyCourse(uuid)
            countRow == 1
        }
    }

    override suspend fun setMyCourse(uuid: UUID): Boolean {
        try {
            withContext(Dispatchers.IO) {
                clubsDao.insertMyCourse(MyCoursesEntity(uuid = uuid))
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    override suspend fun delMyCourse(schedules: List<CoursesSchedule>): Boolean {
        /***по быстрому накинул код. взял из insert
         * перепроверить как отрабатывает удаление и что возвращает
         * */
        try {
            withContext(Dispatchers.IO) {
                clubsDao.deleteMyCourse(mapperListCoursesScheduleToListUUID(schedules))
            }
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun mapperListCoursesScheduleToListUUID(
        schedules: List<CoursesSchedule>
    ): List<UUID> {
        return schedules.map {
            it.uuid
        }
    }

    private fun mapperMyCoursesEntityToListUUID(
        lisMyCoursesEntity: List<MyCoursesEntity>
    ): List<UUID> {
        return lisMyCoursesEntity.map {
            it.uuid
        }
    }

    private fun mapperCourseScheduleEntityToCourseSchedule(
        coursesScheduleEntity: CoursesScheduleEntity
    ): CoursesSchedule {
        return CoursesSchedule(
            id = coursesScheduleEntity.id,
            uuid = coursesScheduleEntity.uuid ?: UUID.fromString("00000000-0000-0000-0000-000000000000"),
            idCourses = coursesScheduleEntity.idCourses,
            uuidCourses = coursesScheduleEntity.uuidCourses
                ?: UUID.fromString("00000000-0000-0000-0000-000000000000"),
            year = coursesScheduleEntity.year,
            month = coursesScheduleEntity.month,
            day = coursesScheduleEntity.day,
            dayOfWeek = coursesScheduleEntity.dayOfWeek,
            startHour = coursesScheduleEntity.startHour,
            startMinute = coursesScheduleEntity.startMinute,
            endHour = coursesScheduleEntity.endHour,
            endMinute = coursesScheduleEntity.endMinute,
            type = coursesScheduleEntity.type,
            shortType = coursesScheduleEntity.shortType,
        )
    }

    private fun mapperCoursesScheduleEntityToCoursesSchedule(
        listCoursesSchedule: List<CoursesScheduleEntity>
    ): List<CoursesSchedule> {
        return listCoursesSchedule.map {
            CoursesSchedule(
                id = it.id,
                uuid = it.uuid ?: UUID.fromString("00000000-0000-0000-0000-000000000000"),
                idCourses = it.idCourses,
                uuidCourses = it.uuidCourses
                    ?: UUID.fromString("00000000-0000-0000-0000-000000000000"),
                year = it.year,
                month = it.month,
                day = it.day,
                dayOfWeek = it.dayOfWeek,
                startHour = it.startHour,
                startMinute = it.startMinute,
                endHour = it.endHour,
                endMinute = it.endMinute,
                type = it.type,
                shortType = it.shortType,
            )
        }
    }

    private fun mapperClubsSampleToClubsEntity(listClubs: List<ClubsSample>): List<ClubEntity> {
        return listClubs.map {
            ClubEntity(
                uuid = it.uuid,
                clubName = it.clubName,
                type = it.type,
                level = it.level,
                description = it.description,
                length = it.length,
                frequency = it.frequency,
                numberClasses = it.numberClasses,
                totalQuantity = it.totalQuantity,
                about = it.about,
            )
        }
    }

    private fun mapperClubsEntityToClubs(listClubs: List<ClubEntity>): List<Clubs> {
        return listClubs.map {
            Clubs(
                id = it.id,
                uuid = it.uuid,
                title = it.clubName,
                level = it.level,
                numberClasses = it.numberClasses,
                perWeek = it.frequency,
                duration = it.length,
                totalQuantity = it.totalQuantity,
                description = it.description,
                about = it.about,
                isFavorite = it.isFavorites,
                isMyCourse = it.isMyCourse
            )
        }

    }

    private fun mapperClubEntityToClub(clubs: ClubEntity): Clubs {
        return Clubs(
            id = clubs.id,
            uuid = clubs.uuid,
            title = clubs.clubName,
            level = clubs.level,
            numberClasses = clubs.numberClasses,
            perWeek = clubs.frequency,
            duration = clubs.length,
            totalQuantity = clubs.totalQuantity,
            description = clubs.description,
            about = clubs.about,
            isFavorite = clubs.isFavorites,
            isMyCourse = clubs.isMyCourse
        )

    }

    private fun mapperClubDtoOutToClubs(listClubs: List<ClubDtoOut>): List<Clubs> {
        return listClubs.map {
            Clubs(
                id = 0,
                title = it.clubName,
                level = it.level,
                numberClasses = "",
                perWeek = it.frequency,
                duration = it.length,
                totalQuantity = "",
                description = it.description,
                isFavorite = false,
                isMyCourse = false,
            )
        }
    }
}