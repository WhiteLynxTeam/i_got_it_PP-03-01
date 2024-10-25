package site.pnpl.igotit.data.repository

import site.pnpl.igotit.data.dbo.dao.TagsDao
import site.pnpl.igotit.domain.irepository.ITagRepository

class TagRepository(
    private val tagsDao: TagsDao,
    ) : ITagRepository {

}