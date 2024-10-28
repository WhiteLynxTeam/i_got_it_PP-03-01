package site.pnpl.igotit.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerialName
import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.api.TagApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.data.dto.club.response.ClubDtoOut
import site.pnpl.igotit.domain.irepository.IClubRepository
import site.pnpl.igotit.domain.irepository.ITagRepository
import site.pnpl.igotit.domain.models.Clubs
import java.util.UUID

class ClubRepository(
    private val clubApi: ClubApi,
    private val clubsDao: ClubsDao
    ) : IClubRepository {

    override suspend fun getFromApi(): Result<List<Clubs>> {

        val result = clubApi.getClubs()
        val outResult = result.map { mapperClubDtoOutToClubs(it) }
        return outResult
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