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
//        if (result.isSuccess) {
//            val response = result.getOrNull() ?: return result
//            val listId = withContext(Dispatchers.IO) {
//                imagesDao.trunc()
//                imagesDao.insertAll(mapperImagesDtoOutToImagesEntity(response.data))
//            }
//            count = listId.size
//        }

        return result.isSuccess


//        var count = 0
//        val token = tokenStorage.get()
//        if (token.isEmpty()) return false
//        val result = imageApi.download(token = token, 0)
//
//        println("ImageRepository: download result = $result")
//
//        if (result.isSuccess) {
//            val response = result.getOrNull() ?: return false
//            val listId = withContext(Dispatchers.IO) {
//                imagesDao.trunc()
//                imagesDao.insertAll(mapperImagesDtoOutToImagesEntity(response.data))
//            }
//            count = listId.size
//        }
//        return count != 0
    }

    private fun mapperClubDtoOutToClubs(club: ClubDtoOut): Clubs {
        return Clubs(
        title = club.clubName,
        level = club.level,
        numberClasses = "",
        perWeek = club.frequency,
        duration = club.length,
        totalQuantity = "",
        description = club.description,
        )
    }
}