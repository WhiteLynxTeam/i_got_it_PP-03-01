package site.pnpl.igotit.domain.irepository

interface IClubRepository {
    suspend fun getFromApi(): Boolean
}