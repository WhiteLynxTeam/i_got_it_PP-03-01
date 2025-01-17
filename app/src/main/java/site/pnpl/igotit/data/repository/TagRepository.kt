package site.pnpl.igotit.data.repository

import site.pnpl.igotit.data.api.TagApi
import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.domain.irepository.ITagRepository

class TagRepository(
    private val tagsDao: TagsDao,
    private val tagApi: TagApi,
    ) : ITagRepository {

    override suspend fun getFromApi(): Boolean {
        val result = tagApi.getTags()
        return result.isSuccess
    }
}