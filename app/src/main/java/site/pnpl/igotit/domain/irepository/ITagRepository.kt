package site.pnpl.igotit.domain.irepository

interface ITagRepository {
    suspend fun getFromApi(): Boolean
}