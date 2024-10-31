package site.pnpl.igotit.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.data.dto.club.response.ClubDtoOut
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs
import site.pnpl.igotit.domain.sampleListOfClubs

class ClubRepository(
    private val clubApi: ClubApi,
    private val clubsDao: ClubsDao
) : IClubRepository {

    override suspend fun getEntities(type: String): List<Clubs> {
        val result = withContext(Dispatchers.IO) {
            clubsDao.getEntities(type)
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
            clubsDao.trunc()
            clubsDao.insertAll(sampleListOfClubs)
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
            } catch (e: Exception){
                Result.failure(e)
            }
        }
        return result
    }

    private fun mapperClubsEntityToClubs(listClubs: List<ClubEntity>): List<Clubs> {
        return listClubs.map {
            Clubs(
                id = it.id,
                title = it.clubName,
                level = it.level,
                numberClasses = it.numberClasses,
                perWeek = it.frequency,
                duration = it.length,
                totalQuantity = it.totalQuantity,
                description = it.description,
                about = it.about,
                isFavorite = it.isFavorites
            )
        }

    }

    private fun mapperClubEntityToClub(clubs: ClubEntity): Clubs {
        return Clubs(
            id = clubs.id,
            title = clubs.clubName,
            level = clubs.level,
            numberClasses = clubs.numberClasses,
            perWeek = clubs.frequency,
            duration = clubs.length,
            totalQuantity = clubs.totalQuantity,
            description = clubs.description,
            about = clubs.about,
            isFavorite = clubs.isFavorites
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
                isFavorite = false
            )
        }
    }
}