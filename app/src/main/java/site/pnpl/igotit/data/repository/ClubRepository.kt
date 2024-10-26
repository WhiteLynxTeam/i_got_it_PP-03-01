package site.pnpl.igotit.data.repository

import site.pnpl.igotit.data.api.ClubApi
import site.pnpl.igotit.data.api.TagApi
import site.pnpl.igotit.data.dbo.dao.ClubsDao
import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.domain.irepository.ITagRepository

class ClubRepository(
    private val clubApi: ClubApi,
    private val clubsDao: ClubsDao
    ) : ITagRepository {

    override suspend fun getFromApi(): Boolean {
        val result = clubApi.getClubs()
        return result.isSuccess
    }
}