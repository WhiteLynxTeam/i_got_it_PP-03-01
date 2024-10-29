package site.pnpl.igotit.domain.irepository

import site.pnpl.igotit.domain.models.Clubs

interface IClubRepository {
    suspend fun getEntities(type: String): List<Clubs>
    suspend fun getFromApi(): Result<List<Clubs>>
    suspend fun saveSampleClubsToDb(): Boolean
}