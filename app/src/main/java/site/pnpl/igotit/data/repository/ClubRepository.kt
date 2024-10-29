package site.pnpl.igotit.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.entity.ClubEntity
import site.pnpl.igotit.data.dbo.entity.sampleListOfClubs
import site.pnpl.igotit.data.dto.club.response.ClubDtoOut
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.models.Clubs

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

    private fun mapperClubsEntityToClubs(listClubs: List<ClubEntity>): List<Clubs> {
        return listClubs.map {
            Clubs(
                title = it.clubName,
                level = it.level,
                numberClasses = it.numberClasses,
                perWeek = it.frequency,
                duration = it.length,
                totalQuantity = it.totalQuantity,
                description = it.description,
            )
        }

    }

    private fun mapperClubDtoOutToClubs(listClubs: List<ClubDtoOut>): List<Clubs> {
        return listClubs.map {
            Clubs(
                title = it.clubName,
                level = it.level,
                numberClasses = "",
                perWeek = it.frequency,
                duration = it.length,
                totalQuantity = "",
                description = it.description,
            )
        }
    }
}