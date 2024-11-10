package site.pnpl.igotit.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
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
    private val clubsDao: ClubsDao
) : IClubRepository {

    override suspend fun getEntities(type: String?): List<Clubs> {
        val result = withContext(Dispatchers.IO) {
            if (type.isNullOrEmpty()) {
                clubsDao.getMyCourses()
            } else {
                clubsDao.getEntities(type)
            }
        }
        return mapperClubsEntityToClubs(result)
    }

    override suspend fun getEntityById(type: String, id: Int): Clubs {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getEntity(type, id)
        }
        return mapperClubEntityToClub(result)
    }

    override suspend fun saveSampleClubsToDb(): Boolean {
        val result = withContext(Dispatchers.IO) {
            clubsDao.truncClubs()
            clubsDao.truncScheduler()

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
        return withContext(Dispatchers.IO) {
            clubsDao.insertMyCourse(MyCoursesEntity(uuid = uuid))
            true
        }
    }

    override suspend fun getCoursesSchedulerByUuidFromDb(uuid: UUID): List<CoursesSchedule> {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getCoursesSchedulerByUuid(uuid)
        }
        return mapperCoursesScheduleEntityToCoursesSchedule(result)
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